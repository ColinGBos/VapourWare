package vapourdrive.vapourware.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigSettings {
    public static final String CATEGORY_MOD = "vapourware";

    public static final ForgeConfigSpec SERVER_CONFIG;
    //    public static ForgeConfigSpec CLIENT_CONFIG;


    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
//        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        SERVER_BUILDER.comment("VapourWare Settings").push(CATEGORY_MOD);

        setupFirstBlockConfig(SERVER_BUILDER);
//        setupFirstBlockConfig(SERVER_BUILDER, CLIENT_BUILDER);

        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
//        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupFirstBlockConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
//        SERVER_BUILDER.comment("Mixin Settings").push(SUBCATEGORY_MIXINS);
//        REPLACE_CROP_BLOCK_SHAPE = SERVER_BUILDER.comment("Replace the hitbox of Crop Block with a tighter shape (allows access to soil and follows texture better)").define("replaceCropBlockShape", false);
//        REPLACE_BEETROOT_SHAPE = SERVER_BUILDER.comment("Replace the hitbox of BeetRoot Block with a tighter shape (allows access to soil and follows texture better)").define("replaceBeetRootBlockShape", false);
//        REPLACE_POTATO_SHAPE = SERVER_BUILDER.comment("Replace the hitbox of Potato Block with a tighter shape (allows access to soil and follows texture better)").define("replacePotatoBlockShape", false);
//        REPLACE_CARROT_SHAPE = SERVER_BUILDER.comment("Replace the hitbox of Carrot Block with a tighter shape (allows access to soil and follows texture better)").define("replaceCarrotBlockShape", false);
//        SERVER_BUILDER.pop();

    }

}
