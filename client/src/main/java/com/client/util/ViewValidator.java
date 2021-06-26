package com.client.util;

import com.api.entity.*;

import static com.api.entity.Dragon.messenger;

public class ViewValidator {

    public static Dragon validateAndCreate(
            String name,
            String coordinates,
            String age,
            String color,
            String type,
            String character,
            String depth,
            String numberOfTreasure,
            String user) throws Exception {

        return new Dragon(
                readName(name),
                readCoordinates(coordinates),
                readAge(age),
                readColor(color),
                readDragonType(type),
                readDragonCharacter(character),
                new DragonCave(
                    readDepth(depth),
                    readNumberOfTreasures(numberOfTreasure)
                ),
                user
        );
    }

    private static String readName(String name) throws Exception {
        try {
            if(name.equals("")) throw new IllegalArgumentException();
            return name;
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    private static Coordinates readCoordinates(String coordinates) throws Exception {
        try {
            String[] arr = coordinates.split(" ");
            Double x = Double.parseDouble(arr[0]);
            int y = Integer.parseInt(arr[1]);
            return new Coordinates(x,y);
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    private static long readAge(String age) throws Exception {
        try {

            long result = Long.parseLong(age);
            if(result <= 0) throw new IllegalArgumentException(messenger.getMessage("inputGreaterZero"));
            return result;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    private static Color readColor(String response) throws Exception {
        try {
            Color organizationType = null;
            if(!response.equals("")) {
                organizationType = Color.valueOf(response);
            }
            return organizationType;
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    private static DragonType readDragonType(String response) throws Exception {
        try {
            DragonType organizationType;
            if(!response.equals("")) {
                organizationType = DragonType.valueOf(response);
            } else {
                throw new Exception(messenger.getMessage("notNullField"));
            }
            return organizationType;
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    private static DragonCharacter readDragonCharacter(String response) throws Exception {
        try {
            DragonCharacter organizationType;
            if(!response.equals("")) {
                organizationType = DragonCharacter.valueOf(response);
            } else {
                throw new Exception(messenger.getMessage("notNullField"));
            }
            return organizationType;
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    private static long readDepth(String result) throws Exception {
        try {
            return Long.parseLong(result);
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    private static Long readNumberOfTreasures(String number) throws Exception {
        try {
            Long result = Long.parseLong(number);
            if(result <= 0) {
                throw new IllegalArgumentException(messenger.getMessage("inputGreaterZero"));
            }
            return result;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }
}
