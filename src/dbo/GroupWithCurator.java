package dbo;

import utils.resources.StringHelper;
import java.util.List;

public class GroupWithCurator {
    private final int id;
    private final String name;
    private final String curatorFio;

    public static String groupTableName = "groups_name";
    public static String curatorTableName = "curators";

    public GroupWithCurator(int id, String name, String curatorFio){
        this.id = id;
        this.name = name;
        this.curatorFio = curatorFio;
    }

    public int getId() { return this.id; }
    public String getName(){
        return this.name;
    }
    public String getCuratorFio() { return this.curatorFio; }

    public static void render(List<GroupWithCurator> items) {
        // Рендер шапки таблицы
        System.out.println("┌----┬-------------------------------------------┬--------------------------------┐");
        System.out.println("│ id │ group name                                │ curatorFio                     │");
        System.out.println("├----┼-------------------------------------------┼--------------------------------┤");
        // Рендер строк таблицы
        int index = 0;
        for (GroupWithCurator item : items) {
            index++;
            String id = Integer.toString(item.getId());
            if (id.length() < 2) {
                id = " " + id;
            }
            String groupName = item.getName() + StringHelper.getSpaces(41 - item.getName().length());
            String curatorFio = item.getCuratorFio() + StringHelper.getSpaces(30 - item.getCuratorFio().length());
            System.out.printf("| %s | %s | %s |%n", id, groupName, curatorFio);
            if (index == items.size()) {
                // Рендер последней строки
                System.out.println("└----┴-------------------------------------------┴--------------------------------┘");
            } else {
                System.out.println("├----┼-------------------------------------------┼--------------------------------┤");
            }
        }
    }

}
