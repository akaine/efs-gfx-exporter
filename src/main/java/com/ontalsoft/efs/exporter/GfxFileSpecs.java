package com.ontalsoft.efs.exporter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum GfxFileSpecs {

    // BIN ====================================================================

    BUTTON_GALAXY("BIN", "EFSBUT2", 23, 23, 3, Type.BIN, 1, false, null, "Galaxy button", 1_578L),
    BUTTON_LAUNCH("BIN", "EFSBUT3", 23, 41, 4, Type.BIN, 1, false, null, "Launch button", 3_772L),
    BUTTON_GROUPALL("BIN", "EFSBUT4", 30, 14, 4, Type.BIN, 1, false, null, "Group all button", 1_680L),
    BUTTON_ARROW_RIGHT("BIN", "EFSBUT5", 41, 14, 4, Type.BIN, 1, false, null, "Right arrow button", 2_296L),
    BUTTON_VIEW_STACK("BIN", "EFSBUT6", 30, 14, 4, Type.BIN, 1, false, null, "View stack button", 1_680L),
    BUTTON_TRAIT("BIN", "EFSBUT7", 25, 25, 2, Type.BIN, 1, false, null, "Select trait button", 1_250L),
    BUTTON_SENTRY("BIN", "EFSBUT8", 25, 25, 4, Type.BIN, 1, false, null, "Sentry button", 2_500L),
    BUTTON_NEXT_UNIT("BIN", "EFSBUT9", 25, 25, 4, Type.BIN, 1, false, null, "Next unit button", 2_500L),
    BUTTON_SKIP_UNIT("BIN", "EFSBUT10", 25, 25, 4, Type.BIN, 1, false, null, "Skip unit button", 2_500L),
    BUTTON_MOVE("BIN", "EFSBUT11", 25, 25, 4, Type.BIN, 1, false, null, "Move unit button", 2_500L),
    BUTTON_END_TURN("BIN", "EFSBUT16", 54, 50, 3, Type.BIN, 1, false, null, "End turn button", 8_100L),
    BUTTON_SCROLL("BIN", "SCROLL", 16, 16, 5, Type.BIN, 1, false, null, "Scroll bar elements", 1_280L),

    ICON_RESOURCES("BIN", "CARGO", 34, 29, 13, Type.BIN, 13, false, null, "Resources icons", 12_818L),
    ICON_COUPLING("BIN", "COUPLE", 20, 31, 1, Type.BIN, 1, false, null, "Cargo coupling", 620L),
    ICON_RELATION("BIN", "EFSBUT1", 27, 27, 2, Type.BIN, 1, false, null, "Faction relation icon", 1_458L),
    ICON_VETERANCY("BIN", "EFSBUT12", 23, 12, 2, Type.BIN, 1, false, null, "Veternacy icons", 552L),
    ICON_LOYALTY("BIN", "EFSBUT13", 15, 13, 2, Type.BIN, 1, false, null, "Loyalty icon", 390L),
    ICON_THUMB("BIN", "EFSBUT14", 27, 27, 2, Type.BIN, 1, false, null, "Thumb up/down icon", 1_458L),
    ICON_LIFE("BIN", "EFSBUT15", 15, 13, 5, Type.BIN, 1, false, null, "Life icon", 975L),
    ICON_FIST("BIN", "FIST", 32, 32, 1, Type.BIN, 1, false, null, "Fist icon", 1_024L),
    ICON_SURRENDER("BIN", "FLAG", 40, 40, 1, Type.BIN, 1, false, null, "Surrender flag icon", 1_600L),
    ICON_DEATH("BIN", "SKULL", 40, 40, 1, Type.BIN, 1, false, null, "Skull icon", 1_600L),
    ICON_PLAYER_COMPUTER("BIN", "PLYCOMP", 48, 64, 1, Type.BIN, 1, false, null, "Computer player icon", 3_072L),
    ICON_PLAYER_HUMAN("BIN", "PLYHUMAN", 48, 64, 1, Type.BIN, 1, false, null, "Human player icon", 3_072L),

    ICON_UNITS("BIN", "EFSUNIT", 32, 32, 92, Type.BIN, 16, false, null, "Units icons", 94_208L),
    ICON_POD("BIN", "POD", 24, 9, 1, Type.BIN, 1, false, null, "Cargo pod icon", 216L),

    BANNER_VACANT("BIN", "BANNERS", 120, 123, 1, Type.BIN, 1, false, null, "Vacant regent banner", 14_760L),
    BANNER_CHURCH("BIN", "BNRCHU", 100, 100, 1, Type.BIN, 1, false, null, "Church banner", 10_000L),
    BANNER_LEAGUE("BIN", "BNRLEG", 100, 100, 1, Type.BIN, 1, false, null, "League banner", 10_000L),
    BANNER_REBELS("BIN", "BNRREB", 100, 100, 1, Type.BIN, 1, false, null, "Rebels banner", 10_000L),
    BANNER_VAU("BIN", "BNRVAU", 100, 100, 1, Type.BIN, 1, false, null, "Vau banner", 10_000L),
    BANNER_EYE("BIN", "EYE100", 100, 100, 1, Type.BIN, 1, false, null, "Imperial Eye banner", 10_000L),
    BANNER_FLEET("BIN", "FLET100", 100, 100, 1, Type.BIN, 1, false, null, "Imperial Fleet banner", 10_000L),
    BANNER_REGENT("BIN", "RGT100", 100, 100, 1, Type.BIN, 1, false, null, "Regent banner", 10_000L),
    BANNER_STIGMATA("BIN", "STIG100", 100, 100, 1, Type.BIN, 1, false, null, "Stigmata banner", 10_000L),
    BANNER_SYMBIOTS("BIN", "SYM100", 100, 100, 1, Type.BIN, 1, false, null, "Symbiots banner", 10_000L),
    BANNER_LIHALAN("BIN", "HOUSE1", 100, 100, 1, Type.BIN, 1, false, null, "House Li Halan banner", 10_000L),
    BANNER_HAZAT("BIN", "HOUSE2", 100, 100, 1, Type.BIN, 1, false, null, "House Hazat banner", 10_000L),
    BANNER_DECADOS("BIN", "HOUSE3", 100, 100, 1, Type.BIN, 1, false, null, "House Decados banner", 10_000L),
    BANNER_HAWKWOOD("BIN", "HOUSE4", 100, 100, 1, Type.BIN, 1, false, null, "House Hawkwood banner", 10_000L),
    BANNER_ALMALIK("BIN", "HOUSE5", 100, 100, 1, Type.BIN, 1, false, null, "House Al-Malik banner", 10_000L),

    BANNER_EYE_HOUSES("BIN", "EYE", 120, 123, 6, Type.BIN, 3, false, null, "Imperial Eye houses banners", 88_560L),
    BANNER_FLEET_HOUSES("BIN", "FLEET", 120, 123, 6, Type.BIN, 3, false, null, "Imperial Fleet houses banners", 88_560L),
    BANNER_REGENT_HOUSES("BIN", "REGENT", 120, 123, 6, Type.BIN, 3, false, null, "Regent houses banners", 88_560L),
    BANNER_STIGMATA_HOUSES("BIN", "STIGMATA", 120, 123, 6, Type.BIN, 3, false, null, "Stigmata houses banners", 88_560L),

    IMG_PORTRAIT_HOUSES("BIN", "PORTRAIT", 250, 200, 15, Type.BIN, 5, false, null, "Nobles portraits", 750_000L),
    IMG_PORTRAIT_CHURCH("BIN", "PTRATCHU", 250, 200, 5, Type.BIN, 5, false, null, "Church portraits", 250_000L),
    IMG_PORTRAIT_LEAGUE("BIN", "PTRATLEA", 250, 200, 1, Type.BIN, 1, false, null, "League portrait", 50_000L),
    IMG_PORTRAIT_TUTOR("BIN", "PTRATTUT", 250, 200, 1, Type.BIN, 1, false, null, "Tutor portrait", 50_000L),
    IMG_PORTRAIT_VAU("BIN", "PTRATVAU", 250, 200, 1, Type.BIN, 1, false, null, "Vau portrait", 50_000L),

    ANIM_PLANETS("BIN", "EFSPLAN", 32, 32, 128, Type.BIN, 16, false, null, "Planets animations", 131_072L),
    ANIM_EXPLOSION("BIN", "FIRE", 29, 32, 4, Type.BIN, 4, false, null, "Explosion animation", 3_712L),
    ANIM_JUMP("BIN", "JUMP", 34, 34, 7, Type.BIN, 7, false, null, "Jump animation", 8_092L),

    TERRAIN_TEMPERATE("BIN", "EFSTILE0", 48, 40, 134, Type.BIN, 16, true, 1520, "Temperate terrain tiles", 203_680L),
    TERRAIN_MEGACITY("BIN", "EFSTILE1", 48, 40, 134, Type.BIN, 16, true, 1520, "Megacity terrain tiles", 203_680L),
    TERRAIN_ARCTIC("BIN", "EFSTILE2", 48, 40, 134, Type.BIN, 16, true, 1520, "Arctic terrain tiles", 203_680L),
    TERRAIN_JUNGLE("BIN", "EFSTILE3", 48, 40, 134, Type.BIN, 16, true, 1520, "Jungle terrain tiles", 203_680L),
    TERRAIN_VOLCANIC("BIN", "EFSTILE4", 48, 40, 134, Type.BIN, 16, true, 1520, "Volcanic terrain tiles", 203_680L),

    BUILDINGS_TEMPERATE("BIN", "STRUCT0", 48, 40, 32, Type.BIN, 16, true, 1520, "Temperate buildings icons", 48_640L),
    BUILDINGS_MEGACITY("BIN", "STRUCT1", 48, 40, 32, Type.BIN, 16, true, 1520, "Megacity buildings icons", 48_640L),
    BUILDINGS_ARCTIC("BIN", "STRUCT2", 48, 40, 32, Type.BIN, 16, true, 1520, "Arctic buildings icons", 48_640L),
    BUILDINGS_JUNGLE("BIN", "STRUCT3", 48, 40, 32, Type.BIN, 16, true, 1520, "Jungle buildings icons", 48_640L),
    BUILDINGS_VOLCANIC("BIN", "STRUCT4", 48, 40, 32, Type.BIN, 16, true, 1520, "Volcanic buildings icons", 48_640L);

    //    X_BORDER("BIN", "BORDER", 0, 0, 1, Type.BIN, 1, false, null, "??? BORDER", 645L),
    //    X_EXPLORE("BIN", "EXPLORE", 0, 0, 4, Type.BIN, 1, true, null, "??? EXPLORE", 16_720L),
    //    X_GLIMMER("BIN", "GLIMMER", 40, 40, 1, Type.BIN, 1, false, null, "??? GLIMMER", 1_520L),
    //    X_S_BORD("BIN", "S_BORD", 0, 0, 1, Type.BIN, 1, false, null, "??? S_BORD", 645L),
    //    X_SHIELD("BIN", "SHIELD", 0, 0, 1, Type.BIN, 1, false, null, "??? SHIELD", 30L),
    //    X_V_BORD("BIN", "V_BORD", 0, 0, 1, Type.BIN, 1, false, null, "??? V_BORD", 86L),

    // MANOWITZ ===============================================================

    //    BUTTON_CLOSE("MANOWITZ", "CLOSE", 81, 25, 4, Type.BIN, 1, false, null, "Close button", 8_100L),
    //    BUTTON_CONTENTS("MANOWITZ", "CONTENTS", 73, 25, 4, Type.BIN, 1, false, null, "Contents button", 7_300L),
    //    BUTTON_NEXT("MANOWITZ", "NEXT", 89, 41, 4, Type.BIN, 1, false, null, "Next button", 14_596L),
    //    BUTTON_PREV("MANOWITZ", "PREV", 81, 41, 4, Type.BIN, 1, false, null, "Previous button", 13_284L);

    // PCX ====================================================================

    private static final Map<String, Map<String, GfxFileSpecs>> lookup = new HashMap<>();

    private String dir;
    private String name;
    private int frameWidth;
    private int frameHeight;
    private int frameCount;
    private Type type;
    private int outputWidthInFrames;
    private boolean isHexagonal;
    private Integer hexagonPixelsLength;
    private String description;
    private long fileLegth;

    private GfxFileSpecs(
            final String dir,
            final String name,
            final int frameWidth,
            final int frameHeight,
            final int frameCount,
            final Type type,
            final int outputWidthInFrames,
            final boolean isHexagonal,
            final Integer hexagonPixelsLength,
            final String description,
            final long fileLegth) {

        this.dir = dir;
        this.name = name;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameCount = frameCount;
        this.type = type;
        this.outputWidthInFrames = outputWidthInFrames;
        this.isHexagonal = isHexagonal;
        this.hexagonPixelsLength = hexagonPixelsLength;
        this.description = description;
        this.fileLegth = fileLegth;
    }

    static {
        for(final GfxFileSpecs t : EnumSet.allOf(GfxFileSpecs.class)) {
            if( !lookup.containsKey(t.getDir())) {
                lookup.put(t.getDir(), new HashMap<>());
            }
            lookup.get(t.getDir()).put(t.getName(), t);
        }
    }

    public static GfxFileSpecs getByDirAndName(final String dir, final String name) {
        try {
            return lookup.get(dir).get(name);
        }
        catch(final Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static enum Type {
        BIN,
        PCX;
    }
}
