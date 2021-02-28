package com.example.imagenes_heroes;

import java.util.List;

public class pojoHeroe {
    public String image_url;
    public String name;

    public pojoHeroe(String image_url, String name) {
        this.image_url = image_url;
        this.name = name;

    }
    private List<pojoHeroe> heroes;
    public  List<pojoHeroe> agregar(pojoHeroe h){
        heroes.add(h);

        return heroes;
    }

    public List<pojoHeroe> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<pojoHeroe> heroes) {
        this.heroes = heroes;
    }

    public pojoHeroe() {
    }

    public String getImage_url() { return image_url;}

    public void setImage_url(String image_url) {this.image_url = image_url;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

}
