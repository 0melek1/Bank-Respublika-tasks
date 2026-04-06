package march7;

public class Transaction {

    private String type;
    private double amount;
    private String description;



    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public String getType(){
        return type;
    }

    public double getAmount(){
        return amount;
    }

    public String getDescription(){
        return description;
    }


    void setType( String type){
        this.type = type;
    }

    void setAmount( double amount){
        this.amount = amount;
    }

    void setDescription( String description){
        this.description = description;
    }




    void showInfo(){
        System.out.printf(type," ",amount," ",description);
    }

}





