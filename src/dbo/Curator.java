package dbo;

import utils.resources.StringHelper;

import java.util.List;

public class Curator {
   private final String fio;
   private final int id;
   public final static String tableName = "curators";
   public final static String fileName = "curators.table";

   public Curator(int id, String fio){
         this.id = id;
         this.fio= fio;
   }

   public String getFio(){
      return this.fio;
   }

   public int getId(){ return this.id; }

   public static void render(List<Curator> items) {
      // Рендер шапки таблицы
      System.out.println("┌----┬--------------------------------┐");
      System.out.println("│ id │ fio                            │");
      System.out.println("├----┼--------------------------------┤");
      // Рендер строк таблицы
      int index = 0;
      for (Curator item : items) {
         index++;
         String id = Integer.toString(item.getId());
         if (id.length() < 2) {
            id = " " + id;
         }
         String fio = item.getFio() + StringHelper.getSpaces(30 - item.getFio().length());

         System.out.printf("| %s | %s |%n", id, fio);
         if (index == items.size()) {
            // Рендер последней строки
            System.out.println("└----┴--------------------------------┘");
         } else {
            System.out.println("├----┼--------------------------------┤");
         }
      }
   }
}
