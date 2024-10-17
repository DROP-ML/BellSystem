package audio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class JTableSortingExample extends JFrame {

    private JTable jTable;

    public JTableSortingExample() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("JTable Sorting Example");

        // Sample data for the table
        Object[][] data = {
                {"John", "Doe", "10:30:00"},
                {"Jane", "Smith", "09:45:00"},
                {"Bob", "Johnson", "11:15:00"}
        };

        // Column names
        String[] columnNames = {"First Name", "Last Name", "Time"};

        // Create a DefaultTableModel
        TableModel model = new DefaultTableModel(data, columnNames);

        // Create a JTable with the DefaultTableModel
        jTable = new JTable(model);

        // Enable automatic row sorting
        jTable.setAutoCreateRowSorter(true);

        // Get the column index of "Time"
        int columnIndexToSort = jTable.getColumnModel().getColumnIndex("Time");

        // Create a TableRowSorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable.getModel());

        // Set a custom comparator for "Time"
        sorter.setComparator(columnIndexToSort, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime time1 = LocalTime.parse(o1, formatter);
                LocalTime time2 = LocalTime.parse(o2, formatter);
                return time1.compareTo(time2);
            }
        });

        // Set the TableRowSorter to the JTable
        jTable.setRowSorter(sorter);

        // Create a button to trigger sorting
        JButton sortButton = new JButton("Sort Time");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Programmatically set the RowFilter to trigger sorting
                sorter.setRowFilter(null);
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING)));
            }
        });

        // Create a JScrollPane and add the JTable to it
        JScrollPane scrollPane = new JScrollPane(jTable);

        // Add the JScrollPane and button to the JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(sortButton, BorderLayout.SOUTH);

        // Set JFrame size and make it visible
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JTableSortingExample());
    }
}
