
      package defaultrepository.impl.contexts;

      // Component context class for lastverteiler
      public class lastverteilerContext implements defaultrepository.impl.contexts.IlastverteilerContext, java.io.Serializable
      {
         
   protected defaultrepository.IServer benötigt_IServer1_Lastverteiler = null;

   protected defaultrepository.IServer benötigt_IServer2_Lastverteiler = null;

         
         
   public defaultrepository.IServer getRoleBenötigt_IServer1_Lastverteiler() {
   	  if (benötigt_IServer1_Lastverteiler == null) {
   	  	  throw new RuntimeException("Attempt to retrieve unbounded port. Check your architecture! "+
   	  	  		"Role Benötigt_IServer1_Lastverteiler <_6EqiAOuTEeCuhfIsXFGDcQ> RequiringEntity lastverteiler");
      }
      return benötigt_IServer1_Lastverteiler;
   }

   public defaultrepository.IServer getRoleBenötigt_IServer2_Lastverteiler() {
   	  if (benötigt_IServer2_Lastverteiler == null) {
   	  	  throw new RuntimeException("Attempt to retrieve unbounded port. Check your architecture! "+
   	  	  		"Role Benötigt_IServer2_Lastverteiler <_Q4Mx8OuZEeCuhfIsXFGDcQ> RequiringEntity lastverteiler");
      }
      return benötigt_IServer2_Lastverteiler;
   }

         
         
   public void setRoleBenötigt_IServer1_Lastverteiler(defaultrepository.IServer newValue) {
      this.benötigt_IServer1_Lastverteiler = newValue;
   }

   public void setRoleBenötigt_IServer2_Lastverteiler(defaultrepository.IServer newValue) {
      this.benötigt_IServer2_Lastverteiler = newValue;
   }

         

         public lastverteilerContext () {
         }
         
         
         public lastverteilerContext (
            
   defaultrepository.IServer benötigt_IServer1_Lastverteiler
,
   defaultrepository.IServer benötigt_IServer2_Lastverteiler

            
            ) {
            
   this.benötigt_IServer1_Lastverteiler = benötigt_IServer1_Lastverteiler;

   this.benötigt_IServer2_Lastverteiler = benötigt_IServer2_Lastverteiler;

            
         }
         
         
         

      }
   