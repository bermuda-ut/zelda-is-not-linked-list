package GameData;

import Common.Vector2;
import GameObject.Mob;
import org.newdawn.slick.Image;

/**
 * Created by noxm on 25/09/16.
 */
public abstract class MobPresets {
    /*
     * MOB DATA
     * id | mob
     *  0 | bat
     *  1 | zombie
     *  2 | bandit
     *  3 | skeleton
     *  4 | draelic
     */
    private static final int[] HP_DATA = {
            40,
            60,
            40,
            100,
            140,
    };

    private static final int[] CD_DATA = {
            0,
            800,
            200,
            500,
            400,
    };

    private static final int[] COLL_DATA = {
            50,
            50,
            50,
            50,
            50,
    };

    private static final int[] DMG_DATA = {
            0,
            10,
            8,
            16,
            30,
    };

    private static final double[] SPEED_DATA = {
            0.2,
            0.25,
            0.25,
            0.25,
            0.25,
    };

    private static final String[] NAME_DATA = {
            "Giant Bat",
            "Zombie",
            "Bandit",
            "Skeleton",
            "Draelic",
    };

    private static final String[] SPRITE_DATA = {
            "assets/units/dreadbat.png",
            "assets/units/zombie.png",
            "assets/units/bandit.png",
            "assets/units/skeleton.png",
            "assets/units/necromancer.png",
    };

    private static final boolean[] AGGRO_DATA = {
            false,
            true,
            true,
            true,
            true,
    };

    private static boolean mobMovementBasic = false;

    public static void addMobByID(int id, double x, double y, World world) {
        try {
            world.addEntity(new Mob(id, NAME_DATA[id], new Vector2(x, y), new Image(SPRITE_DATA[id]), COLL_DATA[id],
                    SPEED_DATA[id], CD_DATA[id], HP_DATA[id], DMG_DATA[id], mobMovementBasic, AGGRO_DATA[id]));
        } catch (Exception e) {
            System.out.println("No such monster with ID" + id);
            return;
        }
    }

    public static void addAllMobs(World world) {
        final int[] bats = {
            1431,864,
            1154,1321,
            807,2315,
            833,2657,
            1090,3200,
            676,3187,
            836,3966,
            653,4367,
            1343,2731,
            1835,3017,
            1657,3954,
            1054,5337,
            801,5921,
            560,6682,
            1275,5696,
            1671,5991,
            2248,6298,
            2952,6373,
            3864,6695,
            4554,6443,
            4683,6228,
            5312,6606,
            5484,5946,
            6371,5634,
            5473,3544,
            5944,3339,
            6301,3414,
            6388,1994,
            6410,1584,
            5314,274,
        };

        final int[] zombies = {
                681, 3201,
                691, 4360,
                2166, 2650,
                2122, 2725,
                2284, 2962,
                2072, 4515,
                2006, 4568,
                2385, 4629,
                2446, 4590,
                2517, 4532,
                4157, 6730,
                4247, 6620,
                4137, 6519,
                4234, 6449,
                5879, 4994,
                5954, 4928,
                6016, 4866,
                5860, 4277,
                5772, 4277,
                5715, 4277,
                5653, 4277,
                5787, 797,
                5668, 720,
                5813, 454,
                5236, 917,
                5048, 1062,
                4845, 996,
                4496, 575,
                3457, 273,
                3506, 779,
                3624, 1192,
                2931, 1396,
                2715, 1326,
                2442, 1374,
                2579, 1159,
                2799, 1269,
                2768, 739,
                2099, 956,
        };

        final int[] bandits = {
                1889, 2581,
                4502, 6283,
                5248, 6581,
                5345, 6678,
                5940, 3412,
                6335, 3459,
                6669, 260,
                6598, 339,
                6598, 528,
                6435, 528,
                6435, 678,
                5076, 1082,
                5191, 1187,
                4940, 1175,
                4760, 1039,
                4883, 889,
                4427, 553,
                3559, 162,
                3779, 1553,
                3573, 1553,
                3534, 2464,
                3635, 2464,
                3402, 2861,
                3151, 2857,
                3005, 2997,
                2763, 2263,
                2648, 2263,
                2621, 1337,
                2907, 1270,
                2331, 598,
                2987, 394,
                1979, 394,
                2045, 693,
                2069, 1028,
        };

        final int[] skeletons = {
                1255, 2924,
                2545, 4708,
                4189, 6585,
                5720, 622,
                5649, 767,
                5291, 312,
                5256, 852,
                4790, 976,
                4648, 401,
                3628, 1181,
                3771, 1181,
                3182, 2892,
                3116, 3033,
                2803, 2901,
                2850, 2426,
                2005, 1524,
                2132, 1427,
                2242, 1343,
                2428, 771,
                2427, 907,
                2770, 613,
                2915, 477,
                1970, 553,
                2143, 1048,
        };

        int[] boss = {
            2069, 510
        };

        for(int i = 0; i < bats.length; i+=2) {
            addMobByID(0, bats[i], bats[i+1], world);
        }

        for(int i = 0; i < zombies.length; i+=2) {
            addMobByID(1, zombies[i], zombies[i+1], world);
        }

        for(int i = 0; i < bandits.length; i+=2) {
            addMobByID(2, bandits[i], bandits[i+1], world);
        }

        for(int i = 0; i < skeletons.length; i+=2) {
            addMobByID(3, skeletons[i], skeletons[i+1], world);
        }

        for(int i = 0; i < boss.length; i+=2) {
            addMobByID(4, boss[i], boss[i + 1], world);
        }

    }
}
