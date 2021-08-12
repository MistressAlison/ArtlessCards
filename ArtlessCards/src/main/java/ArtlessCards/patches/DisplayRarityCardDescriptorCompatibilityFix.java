//package ArtlessCards.patches;
//
//import basemod.ReflectionHacks;
//import basemod.abstracts.CustomCard;
//import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderFixSwitches;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.GlyphLayout;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.evacipated.cardcrawl.modthespire.lib.*;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.helpers.FontHelper;
//import javassist.CtBehavior;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DisplayRarityCardDescriptorCompatibilityFix {
//    @SpirePatch(clz= RenderFixSwitches.RenderPortraitFrameSwitch.class, method="Prefix")
//    public static class LargeCardFramePatch {
//        @SpireInsertPatch(locator= Locator.class, localvars={"tWidth","tOffset"})
//        public static void Insert(AbstractCard __instance, SpriteBatch sb, float x, float y, Color ___renderColor, @ByRef float[] tWidth, @ByRef float[] tOffset) {
//            String typeText;
//            switch(__instance.type) {
//                case ATTACK:
//                    typeText = AbstractCard.TEXT[0];
//                    break;
//                case SKILL:
//                    typeText = AbstractCard.TEXT[1];
//                    break;
//                case POWER:
//                    typeText = AbstractCard.TEXT[2];
//                    break;
//                case STATUS:
//                    typeText = AbstractCard.TEXT[7];
//                    break;
//                case CURSE:
//                    typeText = AbstractCard.TEXT[3];
//                    break;
//                default:
//                    typeText = AbstractCard.TEXT[5];
//            }
//
//            List<String> descriptors = new ArrayList<>();
//            descriptors.add(typeText);
//            descriptors.addAll(((CustomCard)__instance).getCardDescriptors());
//            if (descriptors.size() > 1) {
//                String text = String.join(" | ", descriptors);
//                GlyphLayout gl = new GlyphLayout();
//                FontHelper.cardTypeFont.getData().setScale(1.0F);
//                gl.setText(FontHelper.cardTypeFont, text);
//                tOffset[0] = (gl.width - 38.0F * Settings.scale) / 2.0F;
//                tWidth[0] = (gl.width - 0.0F) / (32.0F * Settings.scale);
//            }
//        }
//
//        private static class Locator extends SpireInsertLocator {
//            @Override
//            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
//                Matcher finalMatcher = new Matcher.MethodCallMatcher(RenderFixSwitches.class, "dynamicFrameRenderHelper");
//                int[] ret = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
//                ret[0] -= 1;
//                return ret;
//            }
//        }
//    }
//}
