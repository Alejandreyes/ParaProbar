package Modelo;
// Generated 19/05/2016 10:58:30 PM by Hibernate Tools 4.3.1



/**
 * ContactoId generated by hbm2java
 */
public class ContactoId  implements java.io.Serializable {


     private int idusuario;
     private String tipo;

    public ContactoId() {
    }

    public ContactoId(int idusuario, String tipo) {
       this.idusuario = idusuario;
       this.tipo = tipo;
    }
   
    public int getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ContactoId) ) return false;
		 ContactoId castOther = ( ContactoId ) other; 
         
		 return (this.getIdusuario()==castOther.getIdusuario())
 && ( (this.getTipo()==castOther.getTipo()) || ( this.getTipo()!=null && castOther.getTipo()!=null && this.getTipo().equals(castOther.getTipo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdusuario();
         result = 37 * result + ( getTipo() == null ? 0 : this.getTipo().hashCode() );
         return result;
   }   


}

