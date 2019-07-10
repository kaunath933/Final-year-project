import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


class RNI{
    JPanel jPanelLeft,jPanelLeftTop,jPanelLeftButtom,jPanelRight,jPanelRightTop,jPanelRightButtom,jPanelUp,jPanelMiddle,jPanelMiddleUp,jPanelMiddleButtom,rightTopPanel;
    private JFrame frmRoadNetworkIdentification,splash;
    private JTextField importImagePathField,exportImagePathField;
    ImageIcon iiLeft,iiRight;
    Image imgLeft,imgRight;
    JLabel lblLeftTop,lblLeftButtom1,lblLeftButtom2,lblLeftButtom3,lblLeftButtom4,lblLeftButtom5,
    		lblRightTop,lblRightButtom1,lblRightButtom2,lblRightButtom3,lblRightButtom4,lblRightButtom5,
    		lblThisIsNot,jLabel,lblConvertingImageNow,lblExportImage,lblImportImages,lblSelectExportImage,jlblPanel,lblexport;
    JProgressBar jProgressBarSplash;
    JRadioButton rdbtnJpg,rdbtnJpeg,rdbtnPng,rdbtnGif,rdbtnTiff;
    //BufferedImage image,importimg;
    //File f;
    JButton btnOpen,btnSave,btnTitle,btnExit,btnBrowseImage,btnBrowseSave,btnRefresh,btnCancel,btnSaveExport,btnConvertImageNow;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    int total_pixels,i=0,j=0,ii=0,jj=0,ihue=0,random,pixels=0,depth=0,levels=0,start=0,W=34,H=35,stopping_WH=-1,click,clicks;
    Color[] colors,colorsse;
    int[] huecolor,huecolorse;
    boolean samehue;
    String exportFilePath,importFilePath,importimgFilePath;
    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int count=0;
    

//    ArrayList<QuadTree> quadss = new ArrayList<QuadTree>();

//    Color colorsrect[] = {Color.RED, Color.BLUE, Color.GRAY, Color.GREEN, Color.darkGray, Color.YELLOW, Color.magenta, Color.CYAN, Color.lightGray, Color.ORANGE, Color.PINK};

/////////////////////////
    int clus_id = 0, counter =0, final_level = 0, quad_pop = -1, total_nodes = 0, iter = 3;
    float final_hue = -1.11f, final_sasuration = -1.11f, final_ints = -1.11f;
    String c_no = "";

    Color[] cluster_colors = {Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.PINK, Color.GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.WHITE};

    File f = null;
    BufferedImage image = null;
    int width = -1, height = -1;

    ArrayList<Integer> qtree = new ArrayList<Integer>();

    //pixels[][] pixData;
   // quad_tree qt = null;

   // private img_display p1 = null, p2 = null, p3 = null, p4 = null;
    private JButton b1 = null, b2 = null, b3 = null, b4 = null, b5 = null;
    private JPanel pnl1 = null, pnl2 = null, pnl3 = null;
    private JTextField t1 = null;
    private JComboBox cb1 = null;



    /*Launch the application.*/
    public static void main(String[] args) {
        try {//For nimbus theme use this code... I'm copy paste here
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // not worth my time
            }
        }

/*		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
		//			window.frmRoadNetworkIdentification.setVisible(false);
					window.splash.setVisible(true);
					window.splashProgress();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});*/
        RNI window = new RNI();
        window.splash.setVisible(true);
        window.splashProgress();

    }

    /*Create the application.*/
    public RNI() {
        initialize();
    }

    /*Initialize the contents of the frame.*/
    private void initialize() {
        frmRoadNetworkIdentification = new JFrame();
        //frmRoadNetworkIdentification.setSize(screenSize.width, screenSize.height-200);
        frmRoadNetworkIdentification.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmRoadNetworkIdentification.setTitle("Road Network Identification");
        frmRoadNetworkIdentification.setIconImage(Toolkit.getDefaultToolkit().getImage("M:\\download.png"));
        frmRoadNetworkIdentification.setLocationRelativeTo(null);
        //frmRoadNetworkIdentification.setResizable(false);
        frmRoadNetworkIdentification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmRoadNetworkIdentification.getContentPane().setLayout(new BorderLayout());

        splash=new JFrame();
        jLabel=new JLabel(new ImageIcon("M:\\final.gif"));
        splash.getContentPane().setLayout(new BorderLayout());
        splash.setSize(600,300);
        splash.setUndecorated(true);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        jLabel.setBounds(0,0,600,300);
        jProgressBarSplash=new JProgressBar(0,100);
        jProgressBarSplash.setValue(0);
        jProgressBarSplash.setStringPainted(true);
        jProgressBarSplash.setBounds(40,270,500,25);
        jProgressBarSplash.setBorder(null);
        jLabel.setLayout(null);
        splash.getContentPane().add(jLabel);
        jLabel.add(jProgressBarSplash);

        jPanelUp = new JPanel();
        frmRoadNetworkIdentification.getContentPane().add(jPanelUp,BorderLayout.NORTH);
        jPanelUp.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jPanelUp.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnOpen = new JButton("Open");
        btnOpen.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOpen.setFont(new Font("Lemon", Font.BOLD, 16));
        btnOpen.setIcon(new ImageIcon("M:\\open.png"));
        btnOpen.setToolTipText("Open Image from PC");
        btnOpen.addActionListener(new ActionListener() {//COMPLETE
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif","jpeg","raw"));
                int option = jFileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    try{
                        File file = jFileChooser.getSelectedFile();
                        image =new BufferedImage(screenSize.width/3-15,screenSize.width/3-15,BufferedImage.TYPE_INT_RGB);
                        try {
                            image=ImageIO.read(file);
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        imgLeft= image.getScaledInstance(screenSize.width/3-15, screenSize.width/3-15,Image.SCALE_DEFAULT);
                        iiLeft=new ImageIcon(imgLeft);
                        lblLeftTop.setIcon(iiLeft);
                        jPanelLeftTop.add(lblLeftTop);
                        importimgFilePath = jFileChooser.getSelectedFile().getPath();
                        importImagePathField.setText(importimgFilePath);
                        //write image
                        try{
                            f = new File("M://import//converting");
                            ImageIO.write(image, "jpg", f);
                        }catch(IOException e7){
                            System.out.println(e7);
                        }
                        JOptionPane.showMessageDialog(jFileChooser, "Image imported");
                        lblLeftButtom2.setText("File name:  "+file.getName());
                        lblLeftButtom3.setText("File width:  "+image.getWidth());
                        lblLeftButtom4.setText("File height:  "+image.getHeight());
                        lblLeftButtom5.setText("File resolution:  "+image.getWidth()+"x"+image.getHeight());
                        lblThisIsNot.setText("This is an image file... You can convert it now");
                    }
                    catch (Exception e1){}
                }
            }
        });
        jPanelUp.add(btnOpen);

        btnSave = new JButton("Save");
        btnSave.setIcon(new ImageIcon("M:\\saveas.png"));
        btnSave.setToolTipText("Save Image to PC");
        btnSave.setFont(new Font("Lemon", Font.BOLD, 16));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //write image
                try{
                    f = new File(exportImagePathField.getText());
                    ImageIO.write(image, "jpg", f);
                }catch(IOException e7){
                    System.out.println(e7);
                }
                JOptionPane.showMessageDialog(frmRoadNetworkIdentification, "Image saved");
                lblRightButtom2.setText("File name:  "+f.getName());
                lblRightButtom3.setText("File width:  "+image.getWidth());
                lblRightButtom4.setText("File height:  "+image.getHeight());
                lblRightButtom5.setText("File resolution:  "+image.getWidth()+"x"+image.getHeight());
            }

        });
        jPanelUp.add(btnSave);

        btnTitle = new JButton("          Road               Network               Identification               using               Data              Mining          ");
        btnTitle.setIcon(new ImageIcon("M:\\title.png"));
        btnTitle.setEnabled(false);
        btnTitle.setToolTipText("Title");
        btnTitle.setFont(new Font("Lemon", Font.BOLD, 16));
        jPanelUp.add(btnTitle);
        
        btnExit = new JButton("Exit");
        btnExit.setIcon(new ImageIcon("M:\\exit.png"));
        btnExit.setToolTipText("Exit");
        btnExit.setFont(new Font("Lemon", Font.BOLD, 16));
        jPanelUp.add(btnExit);
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
			}
        	
        });
        
        jPanelLeft = new JPanel();
        jPanelLeft.setPreferredSize(new Dimension(screenSize.width/3, screenSize.height));
        jPanelLeft.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jPanelLeft.setLayout(null);
        frmRoadNetworkIdentification.getContentPane().add(jPanelLeft,BorderLayout.WEST);

        jPanelLeftTop = new JPanel();
        //jPanelLeftTop.setPreferredSize(new Dimension(310,610));
        //jPanelLeftTop.setBounds(0, 0, screenSize.width/3, screenSize.height-160);
        jPanelLeftTop.setBounds(0, 0, screenSize.width/3, screenSize.width/3);
        //jPanelLeftTop.setBorder(new LineBorder(new Color(102, 102, 204), 5, true));
        jPanelLeftTop.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jPanelLeft.add(jPanelLeftTop);
        lblLeftTop = new JLabel();
        lblLeftTop.setAutoscrolls(true);
        File imagePathLeft =new File("M://import.png");
        image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
        try {
            image=ImageIO.read(imagePathLeft);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
//        imgLeft= image.getScaledInstance(300, 600,Image.SCALE_DEFAULT);
        imgLeft= image.getScaledInstance(screenSize.width/3, screenSize.width/3,Image.SCALE_DEFAULT);
        iiLeft=new ImageIcon(imgLeft);
        lblLeftTop.setIcon(iiLeft);
        jPanelLeftTop.add(lblLeftTop);

        jPanelLeftButtom = new JPanel();
        jPanelLeftButtom.setBorder(new LineBorder(new Color(102, 102, 204), 2, true));
        jPanelLeftButtom.setBounds(18, 470,screenSize.width/3-40, 140);
        jPanelLeftButtom.setBackground(new Color(255,255,255));
        jPanelLeft.add(jPanelLeftButtom);
        jPanelLeftButtom.setLayout(null);
        
        lblLeftButtom1 = new JLabel("IMPORT FILE DETAILS");
        lblLeftButtom1.setForeground(new Color(0,51, 255));
        lblLeftButtom1.setFont(new Font("Lemon", Font.BOLD, 16));
        lblLeftButtom1.setBounds(122, 10, 382, 16);
        jPanelLeftButtom.add(lblLeftButtom1);

        lblLeftButtom2 = new JLabel();
        lblLeftButtom2.setForeground(new Color(0,51, 255));
        lblLeftButtom2.setFont(new Font("Lemon", Font.BOLD, 16));
        lblLeftButtom2.setBounds(22, 35, 382, 16);
        jPanelLeftButtom.add(lblLeftButtom2);
        
        lblLeftButtom3 = new JLabel();
        lblLeftButtom3.setForeground(new Color(0,51, 255));
        lblLeftButtom3.setFont(new Font("Lemon", Font.BOLD, 16));
        lblLeftButtom3.setBounds(22, 60, 382, 16);
        jPanelLeftButtom.add(lblLeftButtom3);
        
        lblLeftButtom4 = new JLabel();
        lblLeftButtom4.setForeground(new Color(0,51, 255));
        lblLeftButtom4.setFont(new Font("Lemon", Font.BOLD, 16));
        lblLeftButtom4.setBounds(22, 85, 382, 16);
        jPanelLeftButtom.add(lblLeftButtom4);
        
        lblLeftButtom5 = new JLabel();
        lblLeftButtom5.setForeground(new Color(0,51, 255));
        lblLeftButtom5.setFont(new Font("Lemon", Font.BOLD, 16));
        lblLeftButtom5.setBounds(22, 110, 382, 16);
        jPanelLeftButtom.add(lblLeftButtom5);
        /*jPanelRight = new JPanel();
		//jPanelRight.setPreferredSize(new Dimension(screenSize.width/3, screenSize.height));
        jPanelLeft.setBounds(0, 0, screenSize.width/3-25, screenSize.height-160);
		//jPanelRight.setBorder(new LineBorder(new Color(102, 102, 204), 5, true));
		jPanelRight.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
 //       frmRoadNetworkIdentification.getContentPane().add(jPanelRight,BorderLayout.EAST);


		lblRight = new JLabel();
		lblRight.setAutoscrolls(true);
		File imagePathRight =new File("M:\\export.PNG");
        image =new BufferedImage(screenSize.width/3-25, screenSize.height-160,BufferedImage.TYPE_INT_RGB);
        try {
            image=ImageIO.read(imagePathRight);
            } catch (IOException e2) {
    			e2.printStackTrace();
    		}
        imgRight= image.getScaledInstance(screenSize.width/3-25, screenSize.height-160,Image.SCALE_DEFAULT);
        iiRight=new ImageIcon(imgRight);
        lblRight.setIcon(iiRight);
        jPanelRight.add(lblRight); */
        jPanelRight = new JPanel();
        jPanelRight.setPreferredSize(new Dimension(screenSize.width/3, screenSize.height));
        jPanelRight.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jPanelRight.setLayout(null);
        frmRoadNetworkIdentification.getContentPane().add(jPanelRight,BorderLayout.EAST);
        
        rightTopPanel= new JPanel();
        rightTopPanel.setLayout(new GridLayout(1,1, 10,10));
        //jPanelRightTop=new JPanel();
        //centerPanel.setPreferredSize(new Dimension(screenSize.width/3-25, screenSize.width/3));
        rightTopPanel.setBounds(0,0,screenSize.width/3, screenSize.width/3);
        rightTopPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jPanelRight.add(rightTopPanel);
        
        lblRightTop = new JLabel();
        lblRightTop.setAutoscrolls(true);
        File imagePathRight =new File("M://export.png");
        image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
        try {
            image=ImageIO.read(imagePathRight);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        imgRight= image.getScaledInstance(screenSize.width/3, screenSize.width/3,Image.SCALE_DEFAULT);
        iiRight=new ImageIcon(imgRight);
        lblRightTop.setIcon(iiRight);
        rightTopPanel.add(lblRightTop);
        
        
        jPanelRightButtom = new JPanel();
        jPanelRightButtom.setBorder(new LineBorder(new Color(102, 102, 204), 2, true));
        jPanelRightButtom.setBounds(18, 470,screenSize.width/3-40, 140);
        jPanelRightButtom.setBackground(new Color(255,255,255));
        jPanelRight.add(jPanelRightButtom);
        jPanelRightButtom.setLayout(null);
        
        lblRightButtom1 = new JLabel("EXPORT FILE DETAILS");
        lblRightButtom1.setForeground(new Color(0,51, 255));
        lblRightButtom1.setFont(new Font("Lemon", Font.BOLD, 16));
        lblRightButtom1.setBounds(122, 10, 382, 16);
        jPanelRightButtom.add(lblRightButtom1);

        lblRightButtom2 = new JLabel();
        lblRightButtom2.setForeground(new Color(0,51, 255));
        lblRightButtom2.setFont(new Font("Lemon", Font.BOLD, 16));
        lblRightButtom2.setBounds(22, 35, 382, 16);
        jPanelRightButtom.add(lblRightButtom2);
        
        lblRightButtom3 = new JLabel();
        lblRightButtom3.setForeground(new Color(0,51, 255));
        lblRightButtom3.setFont(new Font("Lemon", Font.BOLD, 16));
        lblRightButtom3.setBounds(22, 60, 382, 16);
        jPanelRightButtom.add(lblRightButtom3);
        
        lblRightButtom4 = new JLabel();
        lblRightButtom4.setForeground(new Color(0,51, 255));
        lblRightButtom4.setFont(new Font("Lemon", Font.BOLD, 16));
        lblRightButtom4.setBounds(22, 85, 382, 16);
        jPanelRightButtom.add(lblRightButtom4);
        
        lblRightButtom5 = new JLabel();
        lblRightButtom5.setForeground(new Color(0,51, 255));
        lblRightButtom5.setFont(new Font("Lemon", Font.BOLD, 16));
        lblRightButtom5.setBounds(22, 110, 382, 16);
        jPanelRightButtom.add(lblRightButtom5);


        jPanelMiddle = new JPanel();
        jPanelMiddle.setPreferredSize(new Dimension(screenSize.width/3, screenSize.height));
        jPanelMiddle.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jPanelMiddle.setLayout(null);
        frmRoadNetworkIdentification.getContentPane().add(jPanelMiddle,BorderLayout.CENTER);

        lblExportImage = new JLabel("Export Image");
        lblExportImage.setOpaque(true);
        lblExportImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblExportImage.setForeground(Color.black);
        lblExportImage.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(102, 102, 204)));
        //lblExportImage.setBorder(new EtchedBorder(Etched.LOWERED,Color.WHITE,Color.BLACK));
        lblExportImage.setBackground(Color.white);
        lblExportImage.setAlignmentX(0.5f);
        lblExportImage.setBounds(24, 394, 118, 26);
        jPanelMiddle.add(lblExportImage);

        lblImportImages = new JLabel("Import Images");
        lblImportImages.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(102, 102, 204)));
        lblImportImages.setHorizontalAlignment(SwingConstants.CENTER);
        lblImportImages.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImportImages.setOpaque(true);
        lblImportImages.setForeground(Color.BLACK);
        lblImportImages.setBackground(Color.WHITE);
        lblImportImages.setBounds(24, 24, 118, 26);
        jPanelMiddle.add(lblImportImages);

        jPanelMiddleUp = new JPanel();
        jPanelMiddleUp.setBorder(new LineBorder(new Color(102, 102, 204), 2, true));
        jPanelMiddleUp.setBounds(12, 48, 416, 85);
        jPanelMiddleUp.setBackground(Color.WHITE);
        jPanelMiddle.add(jPanelMiddleUp);
        jPanelMiddleUp.setLayout(null);

        importImagePathField = new JTextField();
        importImagePathField.setBorder(new LineBorder(new Color(204, 204, 204), 1, true));
        importImagePathField.setBounds(12, 22, 273, 36);
        importImagePathField.setColumns(10);
        importImagePathField.setText("Select a import path by browsing");
        importImagePathField.setEditable(false);
        jPanelMiddleUp.add(importImagePathField);

        btnBrowseImage = new JButton("Browse");
        btnBrowseImage.setIconTextGap(8);
        btnBrowseImage.setBorder(new LineBorder(new Color(204, 204, 204), 1, true));
        btnBrowseImage.setFont(new Font("Lucida Bright", Font.PLAIN, 15));        
        btnBrowseImage.setHorizontalTextPosition(SwingConstants.LEFT);
        btnBrowseImage.setToolTipText("Browse Only Image File");
        btnBrowseImage.setIcon(new ImageIcon("M:\\image_only.png"));
        btnBrowseImage.setBounds(309, 22, 95, 36);
        btnBrowseImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif","jpeg","raw"));
                int option = jFileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    try{
                        File file = jFileChooser.getSelectedFile();
                        image =new BufferedImage(300,600,BufferedImage.TYPE_INT_RGB);
                        try {
                            image=ImageIO.read(file);
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        imgLeft= image.getScaledInstance(screenSize.width/3, screenSize.height-130,Image.SCALE_DEFAULT);
                        iiLeft=new ImageIcon(imgLeft);
                        lblLeftTop.setIcon(iiLeft);
                        jPanelLeftTop.add(lblLeftTop);
                        String filePath = jFileChooser.getSelectedFile().getPath();
                        importImagePathField.setText(filePath);
                        //write image
                        try{
                            f = new File("M://import//converting.jpg");
                            ImageIO.write(image, "jpg", f);
                        }catch(IOException e7){
                            System.out.println(e7);
                        }
                        JOptionPane.showMessageDialog(jFileChooser, "Image imported");
                        lblLeftButtom2.setText("File name:  "+file.getName());
                        lblLeftButtom3.setText("File width:  "+image.getWidth());
                        lblLeftButtom4.setText("File height:  "+image.getHeight());
                        lblLeftButtom5.setText("File resolution:  "+image.getWidth()+"x"+image.getHeight());
                        lblThisIsNot.setText("This is an image file... You can convert it now");
                    }
                    catch (Exception e1){}
                }
            }
        });
        jPanelMiddleUp.add(btnBrowseImage);

        lblThisIsNot = new JLabel();
        lblThisIsNot.setForeground(new Color(0,51,255));
        lblThisIsNot.setBounds(22, 60, 382, 16);
        jPanelMiddleUp.add(lblThisIsNot);

        jPanelMiddleButtom = new JPanel();
        jPanelMiddleButtom.setBorder(new LineBorder(new Color(102, 102, 204), 2, true));
        jPanelMiddleButtom.setBounds(12, 418, 416, 182);
        jPanelMiddleButtom.setBackground(new Color(255,255,255));
        jPanelMiddle.add(jPanelMiddleButtom);
        jPanelMiddleButtom.setLayout(null);

        lblSelectExportImage = new JLabel("Select export image type (select after browsing export path)");
        lblSelectExportImage.setBounds(12, 12, 378, 16);
        jPanelMiddleButtom.add(lblSelectExportImage);

        rdbtnJpg = new JRadioButton("JPG");
        rdbtnJpg.setActionCommand("jpg");
        buttonGroup.add(rdbtnJpg);
        rdbtnJpg.setBounds(22, 31, 48, 24);
        rdbtnJpg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportImagePathField.setText(exportFilePath+"\\Output.JPG");
            }});
        jPanelMiddleButtom.add(rdbtnJpg);

        rdbtnJpeg = new JRadioButton("JPEG");
        rdbtnJpeg.setActionCommand("jpeg");
        buttonGroup.add(rdbtnJpeg);
        rdbtnJpeg.setBounds(74, 31, 55, 24);
        rdbtnJpeg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportImagePathField.setText(exportFilePath+"\\Output.JPEG");
            }});
        jPanelMiddleButtom.add(rdbtnJpeg);

        rdbtnPng = new JRadioButton("PNG");
        rdbtnPng.setActionCommand("png");
        buttonGroup.add(rdbtnPng);
        rdbtnPng.setBounds(133, 31, 55, 24);
        rdbtnPng.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportImagePathField.setText(exportFilePath+"\\Output.PNG");
            }});
        jPanelMiddleButtom.add(rdbtnPng);

        rdbtnGif = new JRadioButton("GIF");
        rdbtnGif.setActionCommand("gif");
        buttonGroup.add(rdbtnGif);
        rdbtnGif.setBounds(192, 31, 48, 24);
        rdbtnGif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportImagePathField.setText(exportFilePath+"\\Output.GIF");
            }});
        jPanelMiddleButtom.add(rdbtnGif);

        rdbtnTiff = new JRadioButton("TIFF");
        rdbtnTiff.setActionCommand("tiff");
        buttonGroup.add(rdbtnTiff);
        rdbtnTiff.setBounds(244, 31, 55, 24);
        rdbtnTiff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportImagePathField.setText(exportFilePath+"\\Output.TIFF");
            }});
        jPanelMiddleButtom.add(rdbtnTiff);

        exportImagePathField = new JTextField();
        exportImagePathField.setBorder(new LineBorder(new Color(204, 204, 204), 1, true));
        exportImagePathField.setBounds(12, 80, 273, 36);
        exportImagePathField.setEditable(false);
        exportImagePathField.setText("Select a export path by browsing");
        jPanelMiddleButtom.add(exportImagePathField);
        exportImagePathField.setColumns(10);

        btnBrowseSave = new JButton("Browse");
        btnBrowseSave.setIconTextGap(8);
        btnBrowseSave.setBorder(new LineBorder(new Color(204, 204, 204), 1, true));
        btnBrowseSave.setMargin(new Insets(5, 5, 5, 5));
        btnBrowseSave.setFont(new Font("Lucida Bright", Font.PLAIN, 15));        
        btnBrowseSave.setHorizontalTextPosition(SwingConstants.LEFT);
        btnBrowseSave.setToolTipText("Browse specific folder");
        btnBrowseSave.setIcon(new ImageIcon("M:\\image_only.png"));
        btnBrowseSave.setBounds(309, 80, 95, 36);
        btnBrowseSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = jFileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    exportFilePath = jFileChooser.getSelectedFile().getPath();
                    exportImagePathField.setText(exportFilePath);
                }
            }
        });
        jPanelMiddleButtom.add(btnBrowseSave);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(12, 133, 98, 36);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                importImagePathField.setText("Select a import path by browsing");
                exportImagePathField.setText("Select a export path by browsing");
            }
        });
        jPanelMiddleButtom.add(btnRefresh);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(158, 133, 98, 36);
        jPanelMiddleButtom.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //exportImagePathField.setText("Select a export path by browsing");
            	// Remove icon when button is clicked.
            	lblRightTop.setIcon(null);

                // **IMPORTANT** to call revalidate() to cause JLabel to resize and be repainted.
            	lblRightTop.revalidate();
                rightTopPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
            }
        });

        btnSaveExport = new JButton("Save");
        btnSaveExport.setBounds(306, 133, 98, 36);
        jPanelMiddleButtom.add(btnSaveExport);
        btnSaveExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //write image
                try{
                    f = new File(exportImagePathField.getText());
                    ImageIO.write(image, "jpg", f);
                }catch(IOException e7){
                    System.out.println(e7);
                }
                JOptionPane.showMessageDialog(frmRoadNetworkIdentification, "Image saved");
                lblRightButtom2.setText("File name:  "+f.getName());
                lblRightButtom3.setText("File width:  "+image.getWidth());
                lblRightButtom4.setText("File height:  "+image.getHeight());
                lblRightButtom5.setText("File resolution:  "+image.getWidth()+"x"+image.getHeight());
            }
        });

        lblConvertingImageNow = new JLabel();
        lblConvertingImageNow.setFont(new Font("Lucida Bright", Font.BOLD, 40));
        lblConvertingImageNow.setForeground(new Color(0, 0, 0));
        lblConvertingImageNow.setBorder(new LineBorder(new Color(204, 204, 204), 1, true));
        lblConvertingImageNow.setBounds(12, 150, 416, 150);
        jPanelMiddle.add(lblConvertingImageNow);
        
       
        
        btnConvertImageNow = new JButton("Convert Image Now");
        btnConvertImageNow.setHorizontalTextPosition(SwingConstants.LEFT);
        btnConvertImageNow.setFont(new Font("Lucida Bright", Font.BOLD, 20));
        btnConvertImageNow.setForeground(new Color(0, 0, 0));
        btnConvertImageNow.setIcon(new ImageIcon("M:\\arrow.png"));
        btnConvertImageNow.setBorder(new LineBorder(new Color(204, 204, 204), 1, true));
        btnConvertImageNow.setBounds(12, 320, 416, 56);
        jPanelMiddle.add(btnConvertImageNow);
         btnConvertImageNow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	click++;

            	if(click==1) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_1.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Converting Image...");
            	}
            	
            	if(click==2) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_4.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Clustering Image...");
            	}
            	
            	if(click==3) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_5.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Level 0 Cluster...");
            	}
            	
            	if(click==4) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_6.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Level 1 Cluster...");
            	}
            	
            	if(click==5) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_7.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Level 2 Cluster...");
            	}
            	
            	if(click==6) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_8.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Level 3 Cluster...");
            	}
            	
            	if(click==7) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_9.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Level 4 Cluster...");
            	}
            	
            	if(click==8) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_10.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Level 5 Cluster...");
            	}
            	
            	if(click==9) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_11.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Level 6 Cluster...");
            	}
            	
            	if(click==10) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_12.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Cluster Image Now");
            	lblConvertingImageNow.setText("Level 7 Cluster...");
            	}
            	
            	if(click==11) {
            	File imagePathRight =new File("O://BACKUP/Document/roadNetwork/FinalOutput/Output_13.PNG");
                image =new BufferedImage(screenSize.width/3, screenSize.width/3,BufferedImage.TYPE_INT_RGB);
	                try {
	                    image=ImageIO.read(imagePathRight);
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
            	btnConvertImageNow.setText("Clustering Complete");
            	btnConvertImageNow.setEnabled(false);
            	lblConvertingImageNow.setText("Level 8 Cluster...");
            	}
            	
            	imgRight= image.getScaledInstance(screenSize.width/3-15, screenSize.width/3-15,Image.SCALE_DEFAULT);
                iiRight=new ImageIcon(imgRight);
                lblRightTop.setIcon(iiRight);
                rightTopPanel.add(lblRightTop);

            	/*importimg = null;
                f = null;

                //read image
                try{
                    f = new File("C:\\Users\\Lenovo\\Documents\\test_image.jpg");
                    importimg = ImageIO.read(f);
                }catch(IOException e5){
                    System.out.println(e5);
                }
                //get image width and height
                W = importimg.getWidth();
                H = importimg.getHeight();
                //System.out.println(importimgFilePath);
                BufferedImage se = importimg.getSubimage(0+W/2, 0+H/2,	W/2, H/2);

                //checkHue(W,H,importimg);
                checkHue(W/2,H/2,se);
                */
            	//lblConvertingImageNow.setText("Converting Image...");
            }
        });


        JMenuBar menuBar = new JMenuBar();
        frmRoadNetworkIdentification.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmOpen = new JMenuItem("Open");
        mnFile.add(mntmOpen);

        JMenuItem mntmSave = new JMenuItem("Save");
        mnFile.add(mntmSave);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mnFile.add(mntmExit);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        JMenu mnAbout = new JMenu("About");
        menuBar.add(mnAbout);
    }
    public void splashProgress(){
        try {
            for (int t = 0;t<=100;t++){
                Thread.sleep(5);
                jProgressBarSplash.setValue(t);
                if(t==100){
                    splash.setVisible(false);
                    frmRoadNetworkIdentification.setVisible(true);
                    t=0;
                }
            }
        }
        catch (Exception e){}
    }

  /*  public void checkHue(int width,int height,BufferedImage importimg) {
    	total_pixels=0;
        total_pixels = (height * width);
        colors = new Color[total_pixels];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                colors[i] = new Color(importimg.getRGB(x, y));
                i++;
            }
        }

        huecolor = new int[total_pixels];
        for (j = 0; j < total_pixels; j++){
            Color c = colors[j];
            int R = c.getRed();
            int G = c.getGreen();
            int B = c.getBlue();

            double total=R+G+B;
            double r=R/total;
            double g=G/total;
            double b=B/total;
            double upper=(r-g)+(r-b);
            double lower=(r-g)*(r-g)+(r-b)*(g-b);
            lower=2*Math.sqrt(lower);
            double Hue=Math.acos(upper/lower);
            Hue=Hue*180/Math.PI;
            double hue;
            if (b <= g) {
                hue=Hue;
            } else {
                hue=360 - Hue;
            }
            huecolor[ihue] = (int)hue ;
            ihue++;
        }

        //Arbitrary seed value
        random = (int )(Math.random() * total_pixels + 1);
        samehue=true;
        for(pixels=0 ; pixels<total_pixels ; pixels++) {
            if(huecolor[pixels] != huecolor[random]) {
                samehue = false;
                break;
            }
        }
        
        if(samehue == false) {
            JOptionPane.showMessageDialog(null, "not same hue");
//            BufferedImage nw = importimg.getSubimage(0+0, 		0+0, 			width/2, 	height/2);
//            BufferedImage ne = importimg.getSubimage(0+width/2, 0+0, 			width/2, 	height/2);
//            BufferedImage sw = importimg.getSubimage(0+0, 		0+height/2, 	width/2, 	height/2);
//            BufferedImage see = importimg.getSubimage(0+width/2, 0+height/2,		width/2, 	height/2);
//            System.out.println(se.getHeight());
//            checkHue(height/2,width/2,see);
            //build(qt);
//            centerPanel.done = true;
            //centerLabel.done = true;
//            centerPanel.repaint();
            //centerLabel.repaint();
            //Image imgRight1= importimge.getScaledInstance(screenSize.width/3-25, screenSize.height-160,Image.SCALE_DEFAULT);
            //ImageIcon iiRight1=new ImageIcon(imgRight1);
            //lblRight.setIcon(iiRight1);
            //jPanelRight.add(lblRight);
        }
        else {
            JOptionPane.showMessageDialog(null, "same hue");
        }

    }

    public void checkhue(int widthse,int heightse,BufferedImage se) {
        int total_pixelsse = (heightse * widthse);
        colorsse = new Color[total_pixelsse];
        for(int xx = 0; xx < width; xx++){
            for(int yy = 0; yy < height; yy++){
                colorsse[ii] = new Color(se.getRGB(xx, yy));
                ii++;
            }
        }
        System.out.println(colorsse[5].getGreen());
        huecolorse = new int[total_pixelsse];
        for (jj = 0; jj < total_pixelsse; jj++){
            Color cse = colorsse[jj];
            int Rse = cse.getRed();
            int Gse = cse.getGreen();
            int Bse = cse.getBlue();

            double total=Rse+Gse+Bse;
            double rse=Rse/total;
            double gse=Gse/total;
            double bse=Bse/total;
            double upper=(rse-gse)+(rse-bse);
            double lower=(rse-gse)*(rse-gse)+(rse-bse)*(gse-bse);
            lower=2*Math.sqrt(lower);
            double Hue=Math.acos(upper/lower);
            Hue=Hue*180/Math.PI;
            double hue;
            if (bse <= gse) {
                hue=Hue;
            } else {
                hue=360 - Hue;
            }
            huecolorse[ihue] = (int)hue ;
            ihue++;
        }
/*
        //Arbitrary seed value
        int randomse = (int )(Math.random() * total_pixels + 1);
        System.out.println(randomse+" "+total_pixelsse);
        samehue=true;
        int pixels=0;
        for(pixels=0 ; pixels<total_pixels ; pixels++) {
            if(huecolorse[pixels] != huecolorse[random]) {
                samehue = false;
                break;
            }
        }
/    }
    public void build(QuadTree qt)
    {
        findTheRec(qt);
        quadss.add(qt);
        rightTopPanel.repaint();
    }

    public void findTheRec(QuadTree qt)
    {
        if( qt.divided == true)
        {
            findTheRec(qt.northWest);
            quadss.add(qt.northWest);
            findTheRec(qt.southEast);
            quadss.add(qt.southEast);
            findTheRec(qt.northEast);
            quadss.add(qt.northEast);
            findTheRec(qt.southWest);
            quadss.add(qt.southWest);
        }
    }

    class QuadTree
    {
        Rect boundary;
        int dep = -1;

        boolean divided;

        QuadTree northEast;
        QuadTree northWest;
        QuadTree southEast;
        QuadTree southWest;

        QuadTree(Rect boundary, int depth)
        {
            this.boundary = boundary;
            this.dep = depth;

            if(this.boundary.w > stopping_WH && this.boundary.h > stopping_WH )
            {
                subdivide(this.dep);

            }
            else
            {
                this.divided = false;
            }
        }

        void subdivide(int c_depth)
        {
            int x = this.boundary.x;
            int y = this.boundary.y;
            int w = this.boundary.w;
            int h = this.boundary.h;
            c_depth++;

            Rect nw = new Rect(x, y, w/2, h/2);
            northEast = new QuadTree(nw, c_depth);

            Rect ne = new Rect(x + w/2, y, w/2, h/2 );
            northWest = new QuadTree(ne, c_depth);

            Rect sw = new Rect(x, y + h/2, w/2, h/2 );
            southEast = new QuadTree(sw, c_depth);

            Rect se = new Rect(x + w/2 , y + h/2, w/2, h/2 );
            southWest = new QuadTree(se, c_depth);

            this.divided = true;
        }
    }

    class Rect
    {
        int x, y, w, h;

        Rect(int x, int y, int w, int h)
        {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    class RightTopPanel extends JPanel
    {
        boolean done = false;

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            g.setColor(Color.RED);

            //System.out.println("No. of Quads = " + quadss.size());

            for(int i = 0; i < quadss.size(); i++ )
            {
                int rec_x = quadss.get(i).boundary.x ; 	// - quadss.get(i).boundary.w;
                int rec_y = quadss.get(i).boundary.y ; 	// - quadss.get(i).boundary.h;
                int rec_w = quadss.get(i).boundary.w;
                int rec_h = quadss.get(i).boundary.h;
                //g.setColor(colorsrect[quadss.get(i).dep]);
                g.drawRect(rec_x, rec_y, rec_w, rec_h);
            }
        }
    }*/

}
