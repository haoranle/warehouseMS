package com.view;

import com.view.login.LoginJFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.ClassicBorderPainter;
import org.jvnet.substance.border.SimplisticSoftBorderPainter;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.theme.*;
import org.jvnet.substance.watermark.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class MainClass {

    public static void main(String[] args) {
        try {
            /*// 设置观感
            UIManager
                    .setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
            // 设置水印
            SubstanceLookAndFeel
                    .setCurrentWatermark("org.jvnet.substance.watermark.SubstanceMosaicWatermark");
            // 设置渐变渲染
            SubstanceLookAndFeel
                    .setCurrentGradientPainter("org.jvnet.substance.painter.WaveGradientPainter");*/
            //设置外观

            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());

            JFrame.setDefaultLookAndFeelDecorated(true);

            //设置主题
            //SubstanceLookAndFeel.setCurrentTheme(new SubstanceBarbyPinkTheme());

            //   SubstanceLookAndFeel.setCurrentTheme(new SubstanceLightAquaTheme());
            //SubstanceLookAndFeel.setCurrentTheme(new SubstanceMixTheme());
             SubstanceLookAndFeel.setCurrentTheme(new SubstanceBarbyPinkTheme());
//            SubstanceLookAndFeel.setCurrentTheme(new SubstanceAquaTheme());
//            SubstanceLookAndFeel.setCurrentTheme(new SubstanceAquaTheme());

            //设置按钮外观

            SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());

            //设置水印

              SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
          //  SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBinaryWatermark());


            // SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceMosaicWatermark");

            //设置边框

            SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
            //SubstanceLookAndFeel.setCurrentBorderPainter(new SimplisticSoftBorderPainter());

            //设置渐变渲染

            SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());

            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
           // new HubFrame();
            LoginJFrame.getInstance();
        });

        //MainFrame.getInstance();
    }
}
