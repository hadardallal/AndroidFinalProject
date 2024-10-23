package com.example.AndroidProject.animals;

import android.content.Context;

import com.example.AndroidProject.R;

public class MyDataAnimals
{

    public static String[] getAnimalNames(Context context) {
        return new String[] {
                context.getString(R.string.animal_name_lion),
                context.getString(R.string.animal_name_elephant),
                context.getString(R.string.animal_name_giraffe),
                context.getString(R.string.animal_name_penguin),
                context.getString(R.string.animal_name_zebra),
                context.getString(R.string.animal_name_alligator),
                context.getString(R.string.animal_name_hippopotamus),
                context.getString(R.string.animal_name_monkey)
        };
    }

    public static String[] getAnimalDescriptions(Context context) {
        return new String[] {
                context.getString(R.string.animal_desc_lion),
                context.getString(R.string.animal_desc_elephant),
                context.getString(R.string.animal_desc_giraffe),
                context.getString(R.string.animal_desc_alligator),
                context.getString(R.string.animal_desc_penguin),
                context.getString(R.string.animal_desc_zebra),
                context.getString(R.string.animal_desc_hippopotamus),
                context.getString(R.string.animal_desc_monkey)
        };
    }

    public static Integer[] drawableArray = {
            R.drawable.lion, R.drawable.elephant, R.drawable.giraffe, R.drawable.penguin,
            R.drawable.zebra, R.drawable.alligator, R.drawable.hippo,
            R.drawable.monkey};

    public static Integer[] id = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
}
