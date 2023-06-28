package com.example.thbuoi1;

import java.util.Comparator;

public class Contact {
    private int Id;
    private String name;
    private String phone;

   // public Contact(int id, String name, String phone) {
    //    Id = id;
      //  this.name = name;
     //   this.phone = phone;
   // }

    private boolean status;
    private String lastName;

    public boolean isStatus() {
        return status;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contact(int id, String name, String phone, boolean b) {
       Id = id;
        this.name = name;
        this.phone = phone;
        status = false;
//         String[] str=name.split("\\s");
    //    this.lastName=str[str.length-1];
   }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class NameAsc implements Comparator<Contact>
    {


        @Override
        public int compare(Contact o1, Contact o2) {
            return o1.lastName.compareTo(o2.lastName);
        }
    }
    public static class NameDes implements Comparator<Contact>
    {
        public int compare(Contact a,Contact b)
        {
            return b.lastName.compareTo(a.lastName);
        }
    }
}
