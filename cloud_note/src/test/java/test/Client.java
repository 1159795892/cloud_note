package test;

interface Target {
    void Request();//规定两足插头充电标准，必须插入两足插座
}

class Adaptee {
    public void SpecificRequest() {
        System.out.println("把三足插头插在我身上可以进行充电");
    }
}

class Adapter extends Adaptee implements Target {

    public void Request() {
        // TODO Auto-generated method stub
        
        //先调用父类的SpecificRequest方法，相当于把转换器的一头插在欧洲的三足插座上，
        //另一头就可以给两足插头进行充电了
        super.SpecificRequest();
                
        System.out.println("把两足插头插在我身上可以进行充电");
    }
    
}
public class Client {
    
    public static void charge(Target target) {
        //客户调用 Request 方法进行充电
        target.Request();
    }
    
    public static void main(String[] args) {
        Adapter ad = new Adapter();//在欧洲先买一个转换器
        charge(ad);//利用转换器进行充电
    }
    

}