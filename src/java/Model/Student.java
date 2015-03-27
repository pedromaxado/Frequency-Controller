/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Pedro
 */
public class Student {
    
    private int id;
    private String name;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    public static void sort(ArrayList<Student> students)    {
        //ordena os alunos por ordem alfabética
        Collections.sort(students, new Comparator<Student>() {
            public int compare(final Student object1, final Student object2) {
                return object1.getName().compareTo(object2.getName());
            }
        });
    }
}
