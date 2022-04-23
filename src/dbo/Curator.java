package dbo;

public class Curator {
   public final static String tableName = "curators";
   public final static String fileName = "curators.table";

   private String fio;
   private int id;

   public Curator(int id, String fio){
         this.id = id;
         this.fio= fio;
   }

   public String getFio(){
      return this.fio;
   }

   public int getId(){ return this.id; }
}
