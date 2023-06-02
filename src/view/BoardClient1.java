package view;

import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardClient1 extends javax.swing.JFrame {

    static Integer idNguoiChoi1;
    static Integer idNguoiChoi2;
    final int n = 19, m = 19;
    JButton[][] btn;
    JButton lastMove = null;
    public int diem = 0;
    public int currentPlayer = 1; // add a variable to keep track of the current player

    public BoardClient1() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// board
        plBoardContainer.setLayout(new GridLayout(m, n));
        plBoardContainer.setPreferredSize(new Dimension(826, 826));
        initBoard();
        String[] options = {"Người chơi 1", "Người chơi 2"};
        int result = JOptionPane.showOptionDialog(this, "Chọn người chơi đi trước", "Bắt đầu trận đấu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (result == JOptionPane.YES_OPTION) {
            currentPlayer = 1;
            JOptionPane.showMessageDialog(null, "Người chơi 1 đi trước");
        } else {
            currentPlayer = 2;
            JOptionPane.showMessageDialog(null, "Người chơi 2 đi trước");
        }
    }

    public void initBoard() {
        plBoardContainer.removeAll();
        this.setIconImage(new ImageIcon("src/IMG/icon game.png").getImage());
        this.setTitle("Caro Game");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        btn = new JButton[n + 2][m + 2];
        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                btn[row][column] = createBoardButton(row, column);
                btn[row][column].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                if (e.getSource() == btn[i][j] && !"X".equals(btn[i][j].getText()) && !"O".equals(btn[i][j].getText())) {
                                    if (currentPlayer == 1) {
                                        btn[i][j].setText("X");
                                        btn[i][j].setForeground(Color.RED);
                                        diem++;
                                        if (win(i, j, btn[i][j].getText())) {
                                            btn[i][j].setBackground(Color.red);
                                            JOptionPane.showMessageDialog(null, "Người chơi 1 thắng!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
                                            if (currentPlayer == 1) {
                                                try {
                                                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doangame", "root", "");
                                                    String updateQuery = "UPDATE nguoi_choi SET diem_so = diem_so + 20, so_tran_thang = so_tran_thang + 1 WHERE ID = ?";
                                                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                                                    pstmt.setInt(1, idNguoiChoi1);
                                                    pstmt.executeUpdate();
                                                    pstmt.close();
                                                    conn.close();
                                                } catch (SQLException ex) {
                                                    ex.printStackTrace();
                                                }
                                                try {
                                                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doangame", "root", "");
                                                    String updateQuery = "UPDATE nguoi_choi SET diem_so = diem_so - 10 WHERE id = ?";
                                                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                                                    pstmt.setInt(1, idNguoiChoi2);
                                                    pstmt.executeUpdate();
                                                    pstmt.close();
                                                    conn.close();
                                                } catch (SQLException ex) {
                                                    ex.printStackTrace();
                                                }
                                            } else {
                                                try {
                                                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doangame", "root", "");
                                                    String updateQuery = "UPDATE nguoi_choi SET diem_so = diem_so + 20, so_tran_thang = so_tran_thang + 1 WHERE ID = ?";
                                                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                                                    pstmt.setInt(1, idNguoiChoi2);
                                                    pstmt.executeUpdate();
                                                    pstmt.close();
                                                    conn.close();
                                                } catch (SQLException ex) {
                                                    ex.printStackTrace();
                                                }
                                                try {
                                                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doangame", "root", "");
                                                    String updateQuery = "UPDATE nguoi_choi SET diem_so = diem_so - 10 WHERE ID = ?";
                                                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                                                    pstmt.setInt(1, idNguoiChoi1);
                                                    pstmt.executeUpdate();
                                                    pstmt.close();
                                                    conn.close();
                                                } catch (SQLException ex) {
                                                    ex.printStackTrace();
                                                }
                                            }
                                            for (int i1 = 0; i1 < n; i1++) {
                                                for (int j1 = 0; j1 < m; j1++) {
                                                    btn[i1][j1].setText("");
                                                    btn[i1][j1].setBackground(Color.white);
                                                }
                                            }
                                        }
                                        currentPlayer = 2; // switch to player 2 after player 1 makes a move
                                    } else {
                                        btn[i][j].setText("O");
                                        btn[i][j].setForeground(Color.BLUE);
                                        diem++;
                                        if (win(i, j, btn[i][j].getText())) {
                                            btn[i][j].setBackground(Color.green);
                                            JOptionPane.showMessageDialog(null, "Người chơi 2 thắng!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
                                            JOptionPane.showMessageDialog(null, "Trò Chơi Mới", "Thoát", JOptionPane.INFORMATION_MESSAGE);
                                              if (currentPlayer == 1) {
                                                try {
                                                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doangame", "root", "");
                                                    String updateQuery = "UPDATE nguoi_choi SET diem_so = diem_so + 20, so_tran_thang = so_tran_thang + 1 WHERE ID = ?";
                                                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                                                    pstmt.setInt(1, idNguoiChoi1);
                                                    pstmt.executeUpdate();
                                                    pstmt.close();
                                                    conn.close();
                                                } catch (SQLException ex) {
                                                    ex.printStackTrace();
                                                }
                                                try {
                                                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doangame", "root", "");
                                                    String updateQuery = "UPDATE nguoi_choi SET diem_so = diem_so - 10 WHERE id = ?";
                                                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                                                    pstmt.setInt(1, idNguoiChoi2);
                                                    pstmt.executeUpdate();
                                                    pstmt.close();
                                                    conn.close();
                                                } catch (SQLException ex) {
                                                    ex.printStackTrace();
                                                }
                                            } else {
                                                try {
                                                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doangame", "root", "");
                                                    String updateQuery = "UPDATE nguoi_choi SET diem_so = diem_so + 20, so_tran_thang = so_tran_thang + 1 WHERE ID = ?";
                                                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                                                    pstmt.setInt(1, idNguoiChoi2);
                                                    pstmt.executeUpdate();
                                                    pstmt.close();
                                                    conn.close();
                                                } catch (SQLException ex) {
                                                    ex.printStackTrace();
                                                }
                                                try {
                                                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doangame", "root", "");
                                                    String updateQuery = "UPDATE nguoi_choi SET diem_so = diem_so - 10 WHERE ID = ?";
                                                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                                                    pstmt.setInt(1, idNguoiChoi1);
                                                    pstmt.executeUpdate();
                                                    pstmt.close();
                                                    conn.close();
                                                } catch (SQLException ex) {
                                                    ex.printStackTrace();
                                                }}
                                            for (int i1 = 0; i1 < n; i1++) {
                                                for (int j1 = 0; j1 < m; j1++) {
                                                    btn[i1][j1].setText("");
                                                    btn[i1][j1].setBackground(Color.white);
                                                }
                                            }
                                        }
                                        currentPlayer = 1; // switch to player 1 after player 2 makes a move
                                    }
                                }
                            }
                        }
                    }

                    //kiem tra thang 
                    public boolean win(int x, int y, String name) {
                        int k, j;
                        int d = 0;
                        // kt chieu doc
                        for (k = -4; k <= 4; k++) {
                            if (x + k >= 0 && x + k < n) {
                                if (btn[x + k][y].getText() == name) {
                                    d++;
                                } else if (d < 5) {
                                    d = 0;
                                }
                            }
                        }
                        if (d >= 5) {
                            return true;
                        } else {
                            d = 0;
                        }
                        //xet ngang
                        for (k = -5; k <= 5; k++) {
                            if (y + k >= 0 && y + k < n) {
                                if (btn[x][y + k].getText() == name) {
                                    d++;
                                } else if (d < 5) {
                                    d = 0;
                                }
                            }
                        }
                        if (d >= 5) {
                            return true;
                        } else {
                            d = 0;
                        }
                        //cheo
                        for (k = -4, j = 4; k <= 4 && j >= -4; k++, j--) {
                            if (y + k >= 0 && y + k < n && x + j >= 0 && x + j < m) {
                                if (btn[x + j][y + k].getText() == name) {
                                    d++;
                                } else if (d < 5) {
                                    d = 0;
                                }
                            }
                        }
                        if (d >= 5) {
                            return true;
                        } else {
                            d = 0;
                        }
                        for (k = -4; k <= 4; k++) {
                            if (y + k >= 0 && y + k < n && x + k >= 0 && x + k < m) {
                                if (btn[x + k][y + k].getText() == name) {
                                    d++;
                                } else if (d < 5) {
                                    d = 0;
                                }
                            }
                        }
                        if (d >= 5) {
                            return true;
                        }
                        return false;
                    }
                });
                //khi con trỏ chuột trỏ vào phần tử tương ứng nào
                plBoardContainer.add(btn[row][column]);
            }
        }
    }

    public void setLastMove(int row, int column) {
        lastMove = btn[row][column];
    }

    public void addPoint(int row, int column, String email) {
        if (lastMove != null) {
            lastMove.setBackground(new Color(180, 180, 180));
        }
        lastMove = btn[row][column];
        lastMove.setBackground(Color.yellow);
    }

    public JButton createBoardButton(int row, int column) {
        JButton b = new JButton();
        b.setFocusPainted(false);
        b.setBackground(new Color(180, 180, 180));
        b.setActionCommand("");
        return b;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plBoardContainer = new javax.swing.JPanel();
        plScore = new javax.swing.JPanel();
        lbPlayer1 = new javax.swing.JLabel();
        koten = new javax.swing.JLabel();
        lbPlayer2 = new javax.swing.JLabel();
        plPlayer = new javax.swing.JPanel();
        lbAvartar2 = new javax.swing.JLabel();
        lbAvartar1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jbUndo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game Caro");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        plBoardContainer.setPreferredSize(new java.awt.Dimension(826, 826));

        javax.swing.GroupLayout plBoardContainerLayout = new javax.swing.GroupLayout(plBoardContainer);
        plBoardContainer.setLayout(plBoardContainerLayout);
        plBoardContainerLayout.setHorizontalGroup(
            plBoardContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 757, Short.MAX_VALUE)
        );
        plBoardContainerLayout.setVerticalGroup(
            plBoardContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        plScore.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Tỉ số", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        lbPlayer1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lbPlayer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPlayer1.setText("0");
        plScore.add(lbPlayer1);

        koten.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        koten.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        koten.setText("-");
        plScore.add(koten);

        lbPlayer2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lbPlayer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPlayer2.setText("0");
        plScore.add(lbPlayer2);

        plPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Người chơi", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        lbAvartar2.setBackground(new java.awt.Color(255, 153, 153));
        lbAvartar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153), 2));

        lbAvartar1.setBackground(new java.awt.Color(255, 153, 153));
        lbAvartar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153), 2));

        jLabel1.setText("Player1");

        jLabel2.setText("Player2");

        javax.swing.GroupLayout plPlayerLayout = new javax.swing.GroupLayout(plPlayer);
        plPlayer.setLayout(plPlayerLayout);
        plPlayerLayout.setHorizontalGroup(
            plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plPlayerLayout.createSequentialGroup()
                .addGroup(plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plPlayerLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lbAvartar1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plPlayerLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(plPlayerLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(lbAvartar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
            .addGroup(plPlayerLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        plPlayerLayout.setVerticalGroup(
            plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plPlayerLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbAvartar1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(lbAvartar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(plPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 16))); // NOI18N

        jbUndo.setText("Đánh lại");

        jButton2.setText("Ván mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Trang chủ");

        jButton4.setText("Thoát");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbUndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbUndo)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(plBoardContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(plBoardContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(plScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))))
        );

        plBoardContainer.getAccessibleContext().setAccessibleName("");
        plBoardContainer.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardClient1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton jbUndo;
    private javax.swing.JLabel koten;
    private javax.swing.JLabel lbAvartar1;
    private javax.swing.JLabel lbAvartar2;
    private javax.swing.JLabel lbPlayer1;
    private javax.swing.JLabel lbPlayer2;
    private javax.swing.JPanel plBoardContainer;
    private javax.swing.JPanel plPlayer;
    private javax.swing.JPanel plScore;
    // End of variables declaration//GEN-END:variables
}
