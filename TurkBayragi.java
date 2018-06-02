package com.turkbayragi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;

/**
 * @author Mustafa YILMAZ
 */

public class TurkBayragi extends JFrame{	
	private static final long serialVersionUID = 1L;
	private Color kirmizi = new Color(227,10,23);												//Bayragin resmi kirmizi tonu
	private Color beyaz = new Color(255,255,255);
	
	public void paint(Graphics g) {
		super.paint(g); 
		
		Graphics2D g2d = (Graphics2D) g;
        
		//cizgilerin Netligi
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);    
        g2d.setRenderingHints(rh);
		
        
        g2d.setColor(kirmizi); 													
        Area dikdrtgn = new Area(new Rectangle2D.Double(0, 0, getWidth(), getHeight())); 		//(0,0) noktasindan itibaren ve frame genisliginde dikdortgen olusturur
        g2d.fill(dikdrtgn);
        g2d.setColor(beyaz);																	
        Area disDaire = new Area(new Ellipse2D.Double(155, 125, 260, 260));						//Hilali cizmek icin olusturulan dis oval
        dikdrtgn.subtract(disDaire);
        g2d.fill(disDaire);
		g2d.setColor(kirmizi);																	
		Area icDaire = new Area (new Ellipse2D.Double(215, 152, 206, 206));						//Dis ovalin icine oval olusturur
		disDaire.subtract(icDaire);
		g2d.fill(icDaire);						
		int i, faz, aci, icfaz, xm, ym, dr, ir; 												
		int koordx[] = new int[10];																//Yildizin x koordinati
		int koordy[] = new int[10]; 															//Yildizin y koordinati
		faz = 39; 																				//Yildizin donus acisi
		aci = (int) (360 / 5);																	//Yildizin uclari arasindaki aci
		icfaz = (int) (aci / 2);																//Yildizin uclari arasindaki aci
		xm = 450; 																				//Yildiz icin cizilen dairelerden buyuk olanin merkez noktasinin x degeri
		ym = 250; 																				//Yildiz icin cizilen dairelerden buyuk olanin merkez noktasinin y degeri
		dr = 60; 																				//Yildiz icin cizilen dairelerden buyuk olanin yari capi
		ir = 23; 																				//Yildiz icin cizilen dairelerden Kucuk olanin yari capi
		for (i = 0; i < koordx.length; i += 2) { 
			koordx[i] = xm + (int) (dr * Math.cos(Math.PI * faz / 180)); 
			koordx[i + 1] = xm + (int) (ir * Math.cos(Math.PI * (icfaz + faz) / 180)); 
			koordy[i] = ym + (int) (dr * Math.sin(Math.PI * faz / 180)); 
			koordy[i + 1] = ym + (int) (ir * Math.sin(Math.PI * (icfaz + faz) / 180)); 
			faz += aci; 
		} 
		g2d.setColor(beyaz); 
		g.fillPolygon(koordx, koordy, koordx.length); 											
	}

	public static void main(String[] args) {
		TurkBayragi tB = new TurkBayragi();
		tB.setTitle("Turk Bayragi");
		tB.setSize(new Dimension(800, 500));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - tB.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - tB.getHeight()) / 2);
	    tB.setLocation(x, y);
		tB.setVisible(true);
		tB.setResizable(false);
		tB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
