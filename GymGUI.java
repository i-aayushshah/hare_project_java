import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class GymGUI extends JFrame implements ActionListener {
    // ArrayList to store GymMember objects
    private ArrayList<GymMember> memberList;

    // Text fields for input
    private JTextField txtId, txtName, txtLocation, txtPhone, txtEmail, txtDOB;
    private JTextField txtMembershipStartDate, txtReferralSource, txtPaidAmount;
    private JTextField txtRemovalReason, txtTrainerName;
    private JTextField txtPlanPrice, txtPremiumCharge, txtDiscountAmount;

    // Radio buttons for gender selection
    private JRadioButton rbMale, rbFemale;
    private ButtonGroup genderGroup;

    // Combo box for plan selection
    private JComboBox<String> cbPlan;

    // Buttons for actions
    private JButton btnAddRegular, btnAddPremium, btnActivate, btnDeactivate;
    private JButton btnMarkAttendance, btnUpgradePlan, btnCalculateDiscount, btnPayDue;
    private JButton btnRevertRegular, btnRevertPremium, btnDisplay, btnClear;
    private JButton btnSaveToFile, btnReadFromFile;

    // Labels
    private JLabel lblTitle, lblMemberInfo, lblActions;
    private JLabel lblId, lblName, lblLocation, lblPhone, lblEmail, lblGender;
    private JLabel lblDOB, lblStartDate, lblPlan, lblPlanPrice, lblTrainer;
    private JLabel lblPremiumCharge, lblPaidAmount, lblReferralSource, lblRemovalReason;
    private JLabel lblDiscountAmount;

    // Panels
    private JPanel mainPanel, infoPanel, actionPanel, headerPanel;

    public GymGUI() {
        // Initialize ArrayList
        memberList = new ArrayList<>();

        // Set up the frame
        setTitle("Gym Membership Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        initComponents();

        // Add components to the frame
        setupLayout();

        // Register action listeners
        registerListeners();

        // Make the frame visible
        setVisible(true);
        setLocationRelativeTo(null); // Center the window
    }

    private void initComponents() {
        // Initialize text fields
        txtId = new JTextField(20);
        txtName = new JTextField(20);
        txtLocation = new JTextField(20);
        txtPhone = new JTextField(20);
        txtEmail = new JTextField(20);
        txtDOB = new JTextField(20);
        txtMembershipStartDate = new JTextField(20);
        txtReferralSource = new JTextField(20);
        txtPaidAmount = new JTextField(20);
        txtRemovalReason = new JTextField(20);
        txtTrainerName = new JTextField(20);

        // Initialize non-editable fields
        txtPlanPrice = new JTextField("6500");
        txtPlanPrice.setEditable(false);
        txtPremiumCharge = new JTextField("50000");
        txtPremiumCharge.setEditable(false);
        txtDiscountAmount = new JTextField("0");
        txtDiscountAmount.setEditable(false);

        // Initialize radio buttons for gender
        rbMale = new JRadioButton("Male");
        rbMale.setSelected(true);
        rbFemale = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        // Initialize combo box for plan
        String[] plans = {"Basic", "Standard", "Deluxe"};
        cbPlan = new JComboBox<>(plans);
        cbPlan.addActionListener(this);

        // Initialize buttons
        btnAddRegular = new JButton("Add Regular Member");
        btnAddPremium = new JButton("Add Premium Member");
        btnActivate = new JButton("Activate Membership");
        btnDeactivate = new JButton("Deactivate Membership");
        btnMarkAttendance = new JButton("Mark Attendance");
        btnUpgradePlan = new JButton("Upgrade Plan");
        btnCalculateDiscount = new JButton("Calculate Discount");
        btnPayDue = new JButton("Pay Due Amount");
        btnRevertRegular = new JButton("Revert Regular Member");
        btnRevertPremium = new JButton("Revert Premium Member");
        btnDisplay = new JButton("Display");
        btnClear = new JButton("Clear");
        btnSaveToFile = new JButton("Save to File");
        btnReadFromFile = new JButton("Read from File");

        // Initialize labels
        lblTitle = new JLabel("Gym Membership Management System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);

        lblMemberInfo = new JLabel("Member Information");
        lblMemberInfo.setFont(new Font("Arial", Font.BOLD, 16));

        lblActions = new JLabel("Actions");
        lblActions.setFont(new Font("Arial", Font.BOLD, 16));

        lblId = new JLabel("ID:");
        lblName = new JLabel("Name:");
        lblLocation = new JLabel("Location:");
        lblPhone = new JLabel("Phone:");
        lblEmail = new JLabel("Email:");
        lblGender = new JLabel("Gender:");
        lblDOB = new JLabel("DOB:");
        lblStartDate = new JLabel("Membership Start Date:");
        lblPlan = new JLabel("Plan:");
        lblPlanPrice = new JLabel("Plan Price:");
        lblTrainer = new JLabel("Personal Trainer:");
        lblPremiumCharge = new JLabel("Premium Charge:");
        lblPaidAmount = new JLabel("Paid Amount:");
        lblReferralSource = new JLabel("Referral Source:");
        lblRemovalReason = new JLabel("Removal Reason:");
        lblDiscountAmount = new JLabel("Discount Amount:");

        // Initialize panels
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(76, 40, 130)); // Purple color as per wireframe

        infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder(""));

        actionPanel = new JPanel(new GridLayout(5, 3, 10, 10));
        actionPanel.setBorder(BorderFactory.createTitledBorder(""));
    }

    private void setupLayout() {
        // Set up header panel
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(lblTitle);
        add(headerPanel, BorderLayout.NORTH);

        // Set up info panel using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add Member Information label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        infoPanel.add(lblMemberInfo, gbc);

        // First column - Left side
        gbc.gridwidth = 1;

        // ID
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(lblId, gbc);

        gbc.gridx = 1;
        infoPanel.add(txtId, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(lblName, gbc);

        gbc.gridx = 1;
        infoPanel.add(txtName, gbc);

        // Location
        gbc.gridx = 0;
        gbc.gridy = 3;
        infoPanel.add(lblLocation, gbc);

        gbc.gridx = 1;
        infoPanel.add(txtLocation, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 4;
        infoPanel.add(lblPhone, gbc);

        gbc.gridx = 1;
        infoPanel.add(txtPhone, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 5;
        infoPanel.add(lblEmail, gbc);

        gbc.gridx = 1;
        infoPanel.add(txtEmail, gbc);

        // DOB
        gbc.gridx = 0;
        gbc.gridy = 6;
        infoPanel.add(lblDOB, gbc);

        gbc.gridx = 1;
        infoPanel.add(txtDOB, gbc);

        // Referral Source
        gbc.gridx = 0;
        gbc.gridy = 7;
        infoPanel.add(lblReferralSource, gbc);

        gbc.gridx = 1;
        infoPanel.add(txtReferralSource, gbc);

        // Second column - Right side
        // Gender
        gbc.gridx = 2;
        gbc.gridy = 1;
        infoPanel.add(lblGender, gbc);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);

        gbc.gridx = 3;
        infoPanel.add(genderPanel, gbc);

        // Membership Start Date
        gbc.gridx = 2;
        gbc.gridy = 2;
        infoPanel.add(lblStartDate, gbc);

        gbc.gridx = 3;
        infoPanel.add(txtMembershipStartDate, gbc);

        // Plan
        gbc.gridx = 2;
        gbc.gridy = 3;
        infoPanel.add(lblPlan, gbc);

        gbc.gridx = 3;
        infoPanel.add(cbPlan, gbc);

        // Plan Price
        gbc.gridx = 2;
        gbc.gridy = 4;
        infoPanel.add(lblPlanPrice, gbc);

        gbc.gridx = 3;
        infoPanel.add(txtPlanPrice, gbc);

        // Personal Trainer
        gbc.gridx = 2;
        gbc.gridy = 5;
        infoPanel.add(lblTrainer, gbc);

        gbc.gridx = 3;
        infoPanel.add(txtTrainerName, gbc);

        // Premium Charge
        gbc.gridx = 2;
        gbc.gridy = 6;
        infoPanel.add(lblPremiumCharge, gbc);

        gbc.gridx = 3;
        infoPanel.add(txtPremiumCharge, gbc);

        // Paid Amount
        gbc.gridx = 2;
        gbc.gridy = 7;
        infoPanel.add(lblPaidAmount, gbc);

        gbc.gridx = 3;
        infoPanel.add(txtPaidAmount, gbc);

        // Removal Reason
        gbc.gridx = 0;
        gbc.gridy = 8;
        infoPanel.add(lblRemovalReason, gbc);

        gbc.gridx = 1;
        infoPanel.add(txtRemovalReason, gbc);

        // Discount Amount
        gbc.gridx = 2;
        gbc.gridy = 8;
        infoPanel.add(lblDiscountAmount, gbc);

        gbc.gridx = 3;
        infoPanel.add(txtDiscountAmount, gbc);

        // Set up action panel
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 4;
        infoPanel.add(lblActions, gbc);

        // Add buttons to action panel
        actionPanel.add(btnAddRegular);
        actionPanel.add(btnAddPremium);
        actionPanel.add(btnActivate);
        actionPanel.add(btnDeactivate);
        actionPanel.add(btnMarkAttendance);
        actionPanel.add(btnUpgradePlan);
        actionPanel.add(btnCalculateDiscount);
        actionPanel.add(btnPayDue);
        actionPanel.add(btnRevertRegular);
        actionPanel.add(btnRevertPremium);
        actionPanel.add(btnDisplay);
        actionPanel.add(btnClear);
        actionPanel.add(btnSaveToFile);
        actionPanel.add(btnReadFromFile);

        // Style the buttons
        styleButtons(btnAddRegular, btnAddPremium, btnActivate, btnDeactivate);
        styleButtons(btnMarkAttendance, btnUpgradePlan, btnCalculateDiscount, btnPayDue);
        styleButtons(btnRevertRegular, btnRevertPremium, btnDisplay, btnClear);
        styleButtons(btnSaveToFile, btnReadFromFile);

        // Special styling for Clear button
        btnClear.setBackground(new Color(220, 53, 69)); // Red color
        btnClear.setForeground(Color.WHITE);

        // Add panels to main panel
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);
    }

    private void styleButtons(JButton... buttons) {
        for (JButton button : buttons) {
            if (button != btnClear) {
                button.setBackground(new Color(76, 40, 130)); // Purple color
                button.setForeground(Color.WHITE);
            }
            button.setFocusPainted(false);
            button.setBorderPainted(false);
        }
    }

    private void registerListeners() {
        btnAddRegular.addActionListener(this);
        btnAddPremium.addActionListener(this);
        btnActivate.addActionListener(this);
        btnDeactivate.addActionListener(this);
        btnMarkAttendance.addActionListener(this);
        btnUpgradePlan.addActionListener(this);
        btnCalculateDiscount.addActionListener(this);
        btnPayDue.addActionListener(this);
        btnRevertRegular.addActionListener(this);
        btnRevertPremium.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnClear.addActionListener(this);
        btnSaveToFile.addActionListener(this);
        btnReadFromFile.addActionListener(this);
        cbPlan.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbPlan) {
            updatePlanPrice();
        } else if (e.getSource() == btnAddRegular) {
            addRegularMember();
        } else if (e.getSource() == btnAddPremium) {
            addPremiumMember();
        } else if (e.getSource() == btnActivate) {
            activateMembership();
        } else if (e.getSource() == btnDeactivate) {
            deactivateMembership();
        } else if (e.getSource() == btnMarkAttendance) {
            markAttendance();
        } else if (e.getSource() == btnUpgradePlan) {
            upgradePlan();
        } else if (e.getSource() == btnCalculateDiscount) {
            calculateDiscount();
        } else if (e.getSource() == btnPayDue) {
            payDueAmount();
        } else if (e.getSource() == btnRevertRegular) {
            revertRegularMember();
        } else if (e.getSource() == btnRevertPremium) {
            revertPremiumMember();
        } else if (e.getSource() == btnDisplay) {
            displayMembers();
        } else if (e.getSource() == btnClear) {
            clearFields();
        } else if (e.getSource() == btnSaveToFile) {
            saveToFile();
        } else if (e.getSource() == btnReadFromFile) {
            readFromFile();
        }
    }

    private void updatePlanPrice() {
        String selectedPlan = (String) cbPlan.getSelectedItem();
        try {
            double price = 0;
            switch (selectedPlan) {
                case "Basic":
                    price = 6500;
                    break;
                case "Standard":
                    price = 12500;
                    break;
                case "Deluxe":
                    price = 18500;
                    break;
            }
            txtPlanPrice.setText(String.valueOf(price));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error setting plan price: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addRegularMember() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            String location = txtLocation.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String gender = rbMale.isSelected() ? "Male" : "Female";
            String dob = txtDOB.getText();
            String startDate = txtMembershipStartDate.getText();
            String referralSource = txtReferralSource.getText();

            // Check if ID already exists
            if (isMemberIdExists(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate required fields
            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() ||
                email.isEmpty() || dob.isEmpty() || startDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create and add regular member
            RegularMember member = new RegularMember(id, name, location, phone, email,
                gender, dob, startDate, referralSource);
            memberList.add(member);

            JOptionPane.showMessageDialog(this, "Regular member added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding member: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPremiumMember() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            String location = txtLocation.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String gender = rbMale.isSelected() ? "Male" : "Female";
            String dob = txtDOB.getText();
            String startDate = txtMembershipStartDate.getText();
            String trainerName = txtTrainerName.getText();

            // Check if ID already exists
            if (isMemberIdExists(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate required fields
            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() ||
                email.isEmpty() || dob.isEmpty() || startDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create and add premium member
            PremiumMember member = new PremiumMember(id, name, location, phone, email,
                gender, dob, startDate, trainerName);
            memberList.add(member);

            JOptionPane.showMessageDialog(this, "Premium member added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding member: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void activateMembership() {
        try {
            int id = Integer.parseInt(txtId.getText());
            GymMember member = findMemberById(id);

            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found with ID: " + id,
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            member.activateMembership();
            JOptionPane.showMessageDialog(this, "Membership activated successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error activating membership: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deactivateMembership() {
        try {
            int id = Integer.parseInt(txtId.getText());
            GymMember member = findMemberById(id);

            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found with ID: " + id,
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            member.deactivateMembership();
            JOptionPane.showMessageDialog(this, "Membership deactivated successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deactivating membership: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markAttendance() {
        try {
            int id = Integer.parseInt(txtId.getText());
            GymMember member = findMemberById(id);

            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found with ID: " + id,
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!member.getActiveStatus()) {
                JOptionPane.showMessageDialog(this, "Member is not active. Please activate membership first.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            member.markAttendance();
            JOptionPane.showMessageDialog(this, "Attendance marked successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error marking attendance: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void upgradePlan() {
        try {
            int id = Integer.parseInt(txtId.getText());
            GymMember member = findMemberById(id);

            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found with ID: " + id,
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!member.getActiveStatus()) {
                JOptionPane.showMessageDialog(this, "Member is not active. Please activate membership first.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the member is a RegularMember
            if (member instanceof RegularMember) {
                RegularMember regularMember = (RegularMember) member;
                String newPlan = (String) cbPlan.getSelectedItem();
                String result = regularMember.upgradePlan(newPlan);
                JOptionPane.showMessageDialog(this, result,
                    "Plan Upgrade", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Only Regular Members can upgrade plans!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error upgrading plan: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateDiscount() {
        try {
            int id = Integer.parseInt(txtId.getText());
            GymMember member = findMemberById(id);

            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found with ID: " + id,
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the member is a PremiumMember
            if (member instanceof PremiumMember) {
                PremiumMember premiumMember = (PremiumMember) member;
                premiumMember.calculateDiscount(); // Assuming this method updates the discount amount internally
                txtDiscountAmount.setText(String.valueOf(premiumMember.getDiscountAmount()));
                JOptionPane.showMessageDialog(this, "Discount calculated successfully!",
                    "Discount Calculation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Only Premium Members can calculate discounts!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error calculating discount: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void payDueAmount() {
        try {
            int id = Integer.parseInt(txtId.getText());
            double amount = Double.parseDouble(txtPaidAmount.getText());
            GymMember member = findMemberById(id);

            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found with ID: " + id,
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the member is a PremiumMember
            if (member instanceof PremiumMember) {
                PremiumMember premiumMember = (PremiumMember) member;
                String result = premiumMember.payDueAmount(amount);
                JOptionPane.showMessageDialog(this, result,
                    "Payment", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Only Premium Members can pay due amounts!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid ID and amount!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error processing payment: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void revertRegularMember() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String reason = txtRemovalReason.getText();

            if (reason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please provide a removal reason!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            GymMember member = findMemberById(id);

            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found with ID: " + id,
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the member is a RegularMember
            if (member instanceof RegularMember) {
                RegularMember regularMember = (RegularMember) member;
                regularMember.revertRegularMember(reason);
                JOptionPane.showMessageDialog(this, "Regular member reverted successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Member with ID " + id + " is not a Regular Member!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error reverting member: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void revertPremiumMember() {
        try {
            int id = Integer.parseInt(txtId.getText());
            GymMember member = findMemberById(id);

            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found with ID: " + id,
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the member is a PremiumMember
            if (member instanceof PremiumMember) {
                PremiumMember premiumMember = (PremiumMember) member;
                premiumMember.revertPremiumMember();
                JOptionPane.showMessageDialog(this, "Premium member reverted successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Member with ID " + id + " is not a Premium Member!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!",
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error reverting member: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayMembers() {
        StringBuilder sb = new StringBuilder();
        for (GymMember member : memberList) {
            sb.append(member.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Member List", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtLocation.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtDOB.setText("");
        txtMembershipStartDate.setText("");
        txtReferralSource.setText("");
        txtPaidAmount.setText("");
        txtRemovalReason.setText("");
        txtTrainerName.setText("");
        txtPlanPrice.setText("6500");
        txtDiscountAmount.setText("0");
        rbMale.setSelected(true);
        cbPlan.setSelectedIndex(0);
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("members.dat"))) {
            oos.writeObject(memberList);
            JOptionPane.showMessageDialog(this, "Members saved to file successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving members to file: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("members.dat"))) {
            memberList = (ArrayList<GymMember>) ois.readObject();
            JOptionPane.showMessageDialog(this, "Members read from file successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error reading members from file: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isMemberIdExists(int id) {
        for (GymMember member : memberList) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private GymMember findMemberById(int id) {
        for (GymMember member : memberList) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GymGUI());
    }
}
