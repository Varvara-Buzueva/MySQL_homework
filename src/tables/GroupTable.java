package tables;

import dbo.Curator;
import dbo.Group;
import dbo.GroupWithCurator;
import utils.resources.ReadCuratorsTable;
import utils.resources.ReadGroupsTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupTable extends TableAbs  implements ITable<Group> {

    private Group groups;

    public GroupTable(String dbType) {
        super(dbType);
        this.dbExecutor.create(Group.tableName, "id int NOT NULL, name VARCHAR(50) NOT NULL, curatorId int NOT NULL, PRIMARY KEY (`id`), FOREIGN KEY (`curatorId`) REFERENCES `curators` (`id`)");
    }

    @Override
    public void fill() {
        ReadGroupsTable reader = new ReadGroupsTable();
        List<Group> data = reader.read(Group.fileName);
        for(Group group: data){
            this.dbExecutor.add(Group.tableName, String.format("%s, '%s', %s", group.getId(), group.getName(), group.getCuratorId()));
        }

    }

    @Override
    public List<Group> list(){
        ResultSet resultSet = this.dbExecutor.get(Group.tableName);
        List<Group> groups = new ArrayList<>();
        try {
            while (resultSet.next()){
                groups.add(new Group(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                ));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return groups;
    }

    public List<GroupWithCurator> getGroupWithCurator(){
        String[] columnNames = new String[3];
        columnNames[0] = String.format("%s.%s", GroupWithCurator.groupTableName, "id");
        columnNames[1] = String.format("%s.%s", GroupWithCurator.groupTableName, "name");
        columnNames[2] = String.format("%s.%s", GroupWithCurator.curatorTableName, "fio");
        String[] tables = new String[2];
        tables[0] = GroupWithCurator.groupTableName;
        tables[1] = GroupWithCurator.curatorTableName;
        ResultSet resultSet = this.dbExecutor.leftJoin(columnNames, tables, String.format("%s.%s", GroupWithCurator.groupTableName, "curatorId"), String.format("%s.%s", GroupWithCurator.curatorTableName, "id"));
        List<GroupWithCurator> groupsWithCurators = new ArrayList<>();

      try {
            while (resultSet.next()){
                groupsWithCurators.add(new GroupWithCurator(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return groupsWithCurators;
    }

    public List<GroupWithCurator> setNewCurator(String oldValue, String newValue){
        String tableName = GroupWithCurator.groupTableName;
        String column = String.format("%s.%s", GroupWithCurator.groupTableName, "id");
        String condition = String.format("id = %s", oldValue);
        this.dbExecutor.update(tableName, column, newValue, condition);
        List<GroupWithCurator> result = getGroupWithCurator();
        return result;
    }

}
