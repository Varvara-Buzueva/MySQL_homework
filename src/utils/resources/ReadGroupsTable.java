package utils.resources;

import dbo.Group;

public class ReadGroupsTable extends ReadTableData<Group>{
    @Override
    protected Group getElement(String[] data) {
        return new Group(new Integer(data[0]), data[1],new Integer(data[2]));
    }
}
