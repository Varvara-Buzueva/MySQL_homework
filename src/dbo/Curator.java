package dbo;

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
}
