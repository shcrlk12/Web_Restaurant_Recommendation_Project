package com.kjwon.foodchoice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
    long id;
    String name;
    int price;
    int likesNumber;
    long foodId;

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;
        return this.id == menu.getId()
                && this.name.equals(menu.getName())
                && this.price == menu.getPrice()
                && this.likesNumber == menu.getLikesNumber()
                && this.foodId == menu.getFoodId()
                ;
    }

    @Override
    public int hashCode(){
        int h = 0;
        h = (int)id + price * 31 + (int)foodId;
        return h;
    }


}
