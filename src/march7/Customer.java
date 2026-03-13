package march7;

public class Customer {


    private String name;
    private String email;
    private int userId;


    public String getName(){
        return name;
    }


    public String email(){
        return email;
    }

    public int getUserId(){
        return userId;
    }


    void setName( String name){
        this.name = name;
    }

    void setEmail( String email){
        this.email=email;
    }


    void setUserId(int userId){
        this.userId = userId;
    }


    Customer(String name,String email,int userId){
        this.name = name;
        this.email = email;
        this.userId = userId;
    }


    void showInfo(){
        System.out.println("Musteri: " + name + "  Email adressi:  " + "id-si: " + userId);
    }

}
