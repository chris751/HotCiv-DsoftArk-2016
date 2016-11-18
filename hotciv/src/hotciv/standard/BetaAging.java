package hotciv.standard;

import hotciv.framework.AgingStrategy;

/**
 * Created by Christian on 18/11/2016.
 */
public class BetaAging implements AgingStrategy {
    int age = 4000;
    @Override
    public void aging() {
        if(age <= 100 && age > 0){
            age += -1;
        }else if(age <= 0 && age > -50){
            age += -1;
        }else if(age <= -50 && age > -1750){
            age += -50;
        }else if(age <= -1750 && age > -1900){
            age += -25;
        }else if (age <= -1900 && age >-1970){
            age += -5;

        }else if(age <= -1970){
            age += -1;
        }
        else{
            age += -100;
        }
    }

    @Override
    public int getAge() {
        if (age < 0){
            return -age;
        }
        else {
            return age;
        }
    }
}
