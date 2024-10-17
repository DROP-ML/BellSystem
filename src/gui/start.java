/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.json.simple.parser.ParseException;

/**
 *
 * @author HP
 */
public class start extends javax.swing.JFrame {

    private static AdvancedPlayer player1;
    private static AdvancedPlayer player2;
    boolean a = false;
    boolean b = false;

    private File audioFile;
    private File audioFile4;
    private File audioFile2;
    private File audioFile3;
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    // Replace with user's desired times
    // Initial delay in seconds

    /**
     * Creates new form start
     */
    public start() {
        initComponents();
        loadTable();
        resetField();
        loadDate();
        checkReadyToRun();
    }
    private String CurrentTime;

    public void updateTimer() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        CurrentTime = sdf.format(new Date());
        jLabel6.setText(CurrentTime);
    }
    private List<JCheckBox> checkboxes5;

    public void checkReadyToRun() {
        checkboxes5 = new ArrayList<>();
        checkboxes5.add(jCheckBox2);
        checkboxes5.add(jCheckBox3);
        checkboxes5.add(jCheckBox4);
        checkboxes5.add(jCheckBox5);
        checkboxes5.add(jCheckBox6);
        checkboxes5.add(jCheckBox7);
        checkboxes5.add(jCheckBox1);
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
        for (JCheckBox checkBox : checkboxes5) {
            if (checkBox.isSelected()) {
                if (checkBox.getText().equals(dayName)) {
                    if (jRadioButton2.isSelected()) {
                        new SwingBackgroupWorker2().execute();
                    }
                }
            }
        }
    }

    public void loadTable() {
        JSONParser parser = new JSONParser();
        jTable1.setAutoCreateRowSorter(true);
        Object obj = "[]";
        try {
            obj = parser.parse(new FileReader("config3.json"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(start.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(start.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray jsonArray = (JSONArray) obj;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        if (!jsonArray.isEmpty()) {
            Vector<Object> dataVector = new Vector<>();
            for (Object alarmObj : jsonArray) {
                JSONObject alarm = (JSONObject) alarmObj;
                String title = (String) alarm.get("Title");
                String time = (String) alarm.get("Time");
                String audio = (String) alarm.get("audio");
                String status = (String) alarm.get("status");
                Vector<Object> row = new Vector<>();
                row.add(title);
                row.add(time);
                row.add(Boolean.valueOf(status));
                row.add(audio);
                dataVector.add(row);
                model.addRow(row);
            }
        }
    }

    public void loadDate() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        jCheckBox1.setSelected(true);
        jCheckBox2.setSelected(true);
        jCheckBox3.setSelected(true);
        jCheckBox4.setSelected(true);
        jCheckBox5.setSelected(true);
        jCheckBox6.setSelected(false);
        jCheckBox7.setSelected(false);
        try {
            obj = parser.parse(new FileReader("date.json"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(start.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(start.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray jsonArray = (JSONArray) obj;
        if (!jsonArray.equals(null)) {
            for (Object alarmObj : jsonArray) {
                JSONObject alarm = (JSONObject) alarmObj;
                String date = (String) alarm.get("Date");
                switch (date) {
                    case "Monday":
                        jCheckBox1.setSelected(true);
                        break;
                    case "Tuesday":
                        jCheckBox2.setSelected(true);
                        break;
                    case "Wednesday":
                        jCheckBox3.setSelected(true);
                        break;
                    case "Thursday":
                        jCheckBox4.setSelected(true);
                        break;
                    case "Friday":
                        jCheckBox5.setSelected(true);
                        break;
                    case "Saturday":
                        jCheckBox6.setSelected(true);
                        break;
                    case "Sunday":
                        jCheckBox7.setSelected(true);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void resetField() {
        jTextField1.setText("");
        jLabel16.setText("");
        String time = "07:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date newTime;
        try {
            newTime = dateFormat.parse(time); // Change "12:34:56" to the desired time   
            jSpinner1.setValue(newTime);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(start.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Automatic Bell System");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(211, 224, 234));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(39, 102, 120));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Bell System");
        jLabel2.setFocusable(false);
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 324, 58));

        jButton10.setBackground(new java.awt.Color(15, 33, 103));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Stop Play");
        jButton10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 51), 2, true));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 612, 337, 45));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFocusable(false);
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(972, 91, 12, 639));

        jButton19.setBackground(new java.awt.Color(22, 135, 167));
        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton19.setForeground(new java.awt.Color(246, 245, 245));
        jButton19.setText("Play");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 170, 337, -1));

        jButton20.setText("Choose Audio");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 106, 172, 52));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("National Anthem");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 103, -1, 52));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Off");
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 103, -1, -1));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("On");
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(911, 103, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel10.setText("Special Announcement");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 445, -1, 37));

        jButton2.setBackground(new java.awt.Color(22, 135, 167));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(246, 245, 245));
        jButton2.setText("Start");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 500, 337, 34));

        jButton3.setText("Choose File");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1184, 445, 149, 37));

        jPanel2.setBackground(new java.awt.Color(211, 224, 234));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(39, 102, 120));
        jLabel11.setText("Administrator");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(39, 102, 120));
        jLabel12.setText("Bell System");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("...");
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(320, 320, 320)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1706, -1));

        jButton4.setText("Add Bell");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 366, 364, 45));
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1339, 136, -1, -1));
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1345, 211, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Time", "Status", "Audio Path"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoscrolls(false);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 129, 589, 471));

        jSpinner1.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
        jSpinner1.setEditor(new javax.swing.JSpinner.DateEditor(jSpinner1, "HH:mm:ss"));
        jSpinner1.setInputVerifier(jSpinner1.getInputVerifier());
        jPanel1.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 234, 321, -1));

        jLabel14.setText("Titlle ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 194, 37, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 191, 321, -1));

        jLabel15.setText("Time");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 237, -1, -1));

        jButton7.setText("Choos Audio");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 268, 364, 34));

        jLabel16.setBackground(new java.awt.Color(204, 204, 255));
        jLabel16.setFocusable(false);
        jLabel16.setOpaque(true);
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 320, 364, 28));

        jCheckBox1.setText("Monday");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 471, 75, -1));

        jCheckBox2.setText("Tuesday");
        jPanel1.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 471, -1, -1));

        jCheckBox3.setText("Wednesday");
        jPanel1.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 471, 87, -1));

        jCheckBox4.setText("Thursday");
        jPanel1.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 497, 96, -1));

        jCheckBox5.setText("Friday");
        jPanel1.add(jCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 497, 66, -1));

        jCheckBox6.setText("Saturday");
        jPanel1.add(jCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 497, 87, -1));

        jCheckBox7.setText("Sunday");
        jPanel1.add(jCheckBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 523, 100, -1));

        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Active Days");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 429, 79, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("School Anthem");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 213, 144, 52));

        jButton22.setText("Choose School Anthem");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1158, 216, 175, 52));

        jButton23.setBackground(new java.awt.Color(22, 135, 167));
        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton23.setForeground(new java.awt.Color(246, 245, 245));
        jButton23.setText("Play");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 280, 337, -1));

        jButton24.setBackground(new java.awt.Color(22, 135, 167));
        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton24.setForeground(new java.awt.Color(246, 245, 245));
        jButton24.setText("Play");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 393, 337, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("Morning Event");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 329, -1, 52));

        jButton25.setText("Choose School Anthem");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1162, 335, 171, 46));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(39, 102, 120));
        jButton1.setText("Save Changes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 612, 150, 41));

        jButton11.setBackground(new java.awt.Color(15, 33, 103));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Stop Play");
        jButton11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 51), 2, true));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 612, 588, 45));

        jButton5.setBackground(new java.awt.Color(0, 204, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Default");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 560, 340, 42));

        jLabel3.setText("Auto Alarm System");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 105, -1, -1));

        jButton6.setBackground(new java.awt.Color(204, 0, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("RESET");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 613, 161, 42));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if (player1 != null && a == true) {
            player1.close();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Audio Files", "mp3")); // Adjust file extensions as needed

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            audioFile = fileChooser.getSelectedFile();
            // Save the chosen file path to the configuration
            AppConfig.setAudioFilePath(audioFile.getAbsolutePath());
            // Optionally display the chosen file path to the user
            JOptionPane.showMessageDialog(this, "Chosen Audio File: " + audioFile.getAbsolutePath());
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Audio Files", "mp3")); // Adjust file extensions as needed

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            audioFile4 = fileChooser.getSelectedFile();
            // Save the chosen file path to the configuration
            AppConfig4.setAudioFilePath(audioFile4.getAbsolutePath());
            // Optionally display the chosen file path to the user
            JOptionPane.showMessageDialog(this, "Chosen Audio File: " + audioFile4.getAbsolutePath());
            // Initialize the Clip object
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String initialFilePath = AppConfig4.getAudioFilePath();

        if (initialFilePath != null) {
            if (a) {
                player1.close();
            }
            System.out.println(a);
            playSound pl = new playSound();

            pl.play1(initialFilePath);

        } else {
            JOptionPane.showMessageDialog(this, "Audio file path not set. Please choose a file.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Audio Files", "mp3")); // Adjust file extensions as needed

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            audioFile3 = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Chosen Audio File: " + audioFile3.getAbsolutePath());
            jLabel16.setText(audioFile3.getAbsolutePath());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Audio Files", "mp3")); // Adjust file extensions as needed

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            audioFile2 = fileChooser.getSelectedFile();
            // Save the chosen file path to the configuration
            AppConfig2.setAudioFilePath(audioFile2.getAbsolutePath());
            // Optionally display the chosen file path to the user
            JOptionPane.showMessageDialog(this, "Chosen Audio File: " + audioFile2.getAbsolutePath());
            // Initialize the Clip object
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:

        String initialFilePath = AppConfig2.getAudioFilePath();

        if (initialFilePath != null) {
            if (a) {
                player1.close();
            }
            System.out.println(a);
            playSound pl = new playSound();

            pl.play1(initialFilePath);

        } else {
            JOptionPane.showMessageDialog(this, "Audio file path not set. Please choose a file.");
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        String initialFilePath = AppConfig3.getAudioFilePath();

        if (initialFilePath != null) {
            if (a) {
                player1.close();
            }
            System.out.println(a);
            playSound pl = new playSound();

            pl.play1(initialFilePath);

        } else {
            JOptionPane.showMessageDialog(this, "Audio file path not set. Please choose a file.");
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Audio Files", "mp3")); // Adjust file extensions as needed

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            audioFile3 = fileChooser.getSelectedFile();
            // Save the chosen file path to the configuration
            AppConfig3.setAudioFilePath(audioFile3.getAbsolutePath());
            // Optionally display the chosen file path to the user
            JOptionPane.showMessageDialog(this, "Chosen Audio File: " + audioFile3.getAbsolutePath());
            // Initialize the Clip object
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void saveinfo(){
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String sts;

            Boolean isChecked = (Boolean) jTable1.getValueAt(i, 2);
            if (!(isChecked == null)) {
                if (isChecked) {
                    sts = "true";
                } else {
                    sts = "false";
                }
            } else {
                sts = "false";
            }

            JSONObject alarm = new JSONObject();
            alarm.put("Title", jTable1.getValueAt(i, 0));
            alarm.put("Time", jTable1.getValueAt(i, 1));
            alarm.put("status", sts);
            alarm.put("audio", jTable1.getValueAt(i, 3));

            jsonArray.add(alarm);
        }

        // Write the data to the JSON file
        try (FileWriter fileWriter = new FileWriter("config3.json")) {
            fileWriter.write(jsonArray.toJSONString());
            JOptionPane.showMessageDialog(this, "Alarms saved to file.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving alarms to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        SelectedDays();
        checkReadyToRun();
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        saveinfo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String title = jTextField1.getText();
        Date selectedDate = (Date) jSpinner1.getValue();

        // Format the date to display the time
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(selectedDate);
        String audioName = jLabel16.getText();

        String btnText = jButton4.getText();
        if (btnText.equals("Update Row")) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int selectedRow = jTable1.getSelectedRow();

            model.setValueAt(title, selectedRow, 0);
            model.setValueAt(formattedTime, selectedRow, 1);
            model.setValueAt(audioName, selectedRow, 3);

            jButton4.setText("Add New");
            resetField();
        } else {

            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error, Enter Name About Alarm", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (audioName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error, Add a Audio to Alarm", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Object[] save = new Object[3];
                save[0] = title;
                save[1] = audioName;
                save[2] = formattedTime;

//        System.out.println(Arrays.toString(save));
                addRowTable(save);
                resetField();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        int row = jTable1.getSelectedRow();

        if (row != -1) {

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            if (evt.getClickCount() == 3) {
                jButton4.setText("Add New");
                resetField();
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    // User clicked OK, delete the row
                    model.removeRow(row);
                } else {
                    // User clicked NO or closed the dialog, do nothing
                }
            } else if (evt.getClickCount() == 2) {
                jButton4.setText("Update Row");
                String title = String.valueOf(jTable1.getValueAt(row, 0));
                String time = String.valueOf(jTable1.getValueAt(row, 1));
//            String status = String.valueOf(jTable1.getValueAt(row, 2));
                String audio = String.valueOf(jTable1.getValueAt(row, 3));

                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Date newTime;
                try {
                    newTime = dateFormat.parse(time); // Change "12:34:56" to the desired time            
                    jSpinner1.setValue(newTime);

                } catch (java.text.ParseException ex) {
                    Logger.getLogger(start.class.getName()).log(Level.SEVERE, null, ex);
                }

                jTextField1.setText(title);
                jLabel16.setText(audio);
//            jTextField1.setText(title);

            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        player2.close();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to set Default Data?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {

            String userInput = JOptionPane.showInputDialog(null, "Type 'default' to confirm:", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

            // Check the user's input
            if (userInput != null && userInput.equalsIgnoreCase("default")) {

                JSONParser parser = new JSONParser();
                jTable1.setAutoCreateRowSorter(true);
                Object obj = "[]";
                try {
                    obj = parser.parse(new FileReader("default.json"));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(start.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IOException | ParseException ex) {
                    Logger.getLogger(start.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                JSONArray jsonArray2 = (JSONArray) obj;
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                if (!jsonArray2.isEmpty()) {
                    model.setRowCount(0);
                    Vector<Vector<Object>> dataVector = new Vector<>();
                    for (Object alarmObj : jsonArray2) {
                        JSONObject alarm = (JSONObject) alarmObj;
                        String title = (String) alarm.get("Title");
                        String time = (String) alarm.get("Time");
                        String audio = (String) alarm.get("audio");
                        String status = (String) alarm.get("status");
                        Vector<Object> row = new Vector<>();
                        row.add(title);
                        row.add(time);
                        row.add(Boolean.valueOf(status));
                        row.add(audio);
                        dataVector.add(row);
                        model.addRow(row);
                    }
                }
                saveinfo();
                loadTable();
                loadDate();

            } else {
                // User input is incorrect or canceled
                System.out.println("Deletion canceled or invalid input.");
            }

        } else {
            loadTable();
            loadDate();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed

        String initialFilePath = AppConfig.getAudioFilePath();//        
//        String initialFilePath = "INTERVAL.mpeg";

        if (initialFilePath != null) {
            if (a) {
                player1.close();
            }
            System.out.println(a);
            playSound pl = new playSound();

            pl.play1(initialFilePath);

        } else {
            JOptionPane.showMessageDialog(this, "Audio file path not set. Please choose a file.");
        }

    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        JSONArray jsonArray = new JSONArray();
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to RESET  Data?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {

            String userInput = JOptionPane.showInputDialog(null, "Type 'delete' to confirm:", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

            // Check the user's input
            if (userInput != null && userInput.equalsIgnoreCase("delete")) {
                // User input is correct
                System.out.println("User confirmed deletion.");

                try (FileWriter fileWriter = new FileWriter("config3.json")) {
                    fileWriter.write(jsonArray.toJSONString());
                    JOptionPane.showMessageDialog(this, "Reset Successfull !!!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error Reset Data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                try (FileWriter fileWriter2 = new FileWriter("date.json")) {
                    fileWriter2.write(jsonArray.toJSONString());
                } catch (IOException e) {
                }
                saveinfo();
                loadTable();
                loadDate();
                
            } else {
                // User input is incorrect or canceled
                System.out.println("Deletion canceled or invalid input.");
            }

        } else {
            loadTable();
            loadDate();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    public void addRowTable(Object[] data) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

//            while (resultSet.next()) {
        Vector v = new Vector();

        v.add(data[0]);
        v.add(data[2]);
        v.add(Boolean.valueOf("true"));
        v.add(data[1]);

        model.addRow(v);
//            }
    }
    public static boolean isrunnng;

    class playSound {

        private void play1(String filePath) {
            new Thread(() -> {
                try {
                    FileInputStream fileInputStream = new FileInputStream(filePath);
                    fileInputStream.close();

                    player1 = new AdvancedPlayer(new FileInputStream(filePath));
                    if (a = true) {
                        player1.play();
                    }
                    a = false;
                } catch (JavaLayerException | IOException e) {
                }
            }).start();
        }

        private void play5(String filePath) {
            new Thread(() -> {
                try {
                    FileInputStream fileInputStream = new FileInputStream(filePath);
                    fileInputStream.close();

                    player2 = new AdvancedPlayer(new FileInputStream(filePath));
                    if (b = true) {
                        player2.play();
                    }
                    b = false;
                } catch (JavaLayerException | IOException e) {
                }
            }).start();
        }
    }

    private List<JCheckBox> checkboxes;

    private void SelectedDays() {
        checkboxes = new ArrayList<>();

        // Assume you already have JCheckBox instances created and stored in checkboxes list
        checkboxes.add(jCheckBox2);
        checkboxes.add(jCheckBox3);
        checkboxes.add(jCheckBox4);
        checkboxes.add(jCheckBox5);
        checkboxes.add(jCheckBox6);
        checkboxes.add(jCheckBox7);
        checkboxes.add(jCheckBox1);

        JSONArray jsonArray = new JSONArray();

        for (JCheckBox checkBox : checkboxes) {
            if (checkBox.isSelected()) {
                JSONObject alarm = new JSONObject();
                alarm.put("Date", checkBox.getText());
                alarm.put("status", "true");

                jsonArray.add(alarm);
            }
        }

        try (FileWriter fileWriter = new FileWriter("date.json")) {
            fileWriter.write(jsonArray.toJSONString());
        } catch (IOException e) {
        }
    }

    public class SwingBackgroupWorker2 extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            while (true) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        runAudioAtTime();
                        updateTimer();
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }

    }

    public int runAudioAtTime() {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("config3.json"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(start.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException | ParseException ex) {
            Logger.getLogger(start.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        JSONArray jsonArray = (JSONArray) obj;
        // Create vector to hold table data
        if (jsonArray != null) {

            for (Object alarmObj : jsonArray) {

                Date currentTime = new Date();
                String currentFormattedTime = TIME_FORMAT.format(currentTime);
//            System.out.println(currentFormattedTime);

                JSONObject alarm = (JSONObject) alarmObj;
                String time = (String) alarm.get("Time");
                String audio = (String) alarm.get("audio");
                String status = (String) alarm.get("status");

                if (status.equals("true") & currentFormattedTime.equals(time)) {
                    String initialFilePath = audio;
                    System.out.println(audio);

                    if (initialFilePath != null) {
                        if (b) {
                            player2.close();
                        }
                        playSound pl = new playSound();
                        b = true;
                        pl.play5(initialFilePath);

                    } else {
                        JOptionPane.showMessageDialog(this, "Audio file path not set. Please choose a file.");
                    }
                }

            }
        }
        return 10;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        FlatIntelliJLaf.setup();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new start().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
