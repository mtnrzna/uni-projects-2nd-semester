package sample;

import java.util.ArrayList;

//this is a interface file which employee file and manager file and company file inherit from
public interface File<T>{



    public abstract void readFile();
    public abstract void save(T t);
    public abstract void updateFile();
    public abstract void remove(T t);
//    public abstract void showDetails(T t);
    public abstract void edit(T t);

}
