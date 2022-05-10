package dbo;

import utils.resources.StringHelper;
import java.util.List;

public class Group {
  private final int id;
  private final String name;
  private final int curatorId;
  public final static String tableName ="groups_name";
  public final static String fileName = "groups.table";

  public Group(int id, String name, int curatorId){
    this.id = id;
    this.name = name;
    this.curatorId = curatorId;
  }

  public int getId() { return this.id; }
  public String getName(){
    return this.name;
  }
  public int getCuratorId() { return this.curatorId; }
  public static void render(List<Group> items) {
    // Рендер шапки таблицы
    System.out.println("┌----┬-------------------------------------------┬-----------┐");
    System.out.println("│ id │ group name                                │ curatorId │");
    System.out.println("├----┼-------------------------------------------┼-----------┤");
    // Рендер строк таблицы
    int index = 0;
    for (Group item : items) {
      index++;
      String id = Integer.toString(item.getId());
      if (id.length() < 2) {
        id = " " + id;
      }
      String groupName = item.getName() + StringHelper.getSpaces(41 - item.getName().length());
      String curatorId = StringHelper.getSpaces((9 - Integer.toString(item.getCuratorId()).length())) + item.getCuratorId();
      System.out.printf("| %s | %s | %s |%n", id, groupName, curatorId);
      if (index == items.size()) {
        // Рендер последней строки
        System.out.println("└----┴-------------------------------------------┴-----------┘");
      } else {
        System.out.println("├----┼-------------------------------------------┼-----------┤");
      }
    }
  }
}
