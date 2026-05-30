import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CarnewSuppliersystem1 extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private final String[] carBrands = {"Toyota", "Mitsubishi", "Ford", "BMW", "Honda", "Mercedes", "Nissan", "Chevrolet"};
    private final Map<String, String[]> carModels = new HashMap<>();
    private final Map<String, String[]> carDetails = new HashMap<>();

    public CarnewSuppliersystem1() {
        setTitle("عالم السيارات - Car Supplier System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        initializeCarData();

        // شاشة الترحيب
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(100, 149, 237));
        JLabel welcomeLabel = new JLabel("مرحباً بك في عالم السيارات!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("بدء التسجيل");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(0, 123, 255));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        welcomePanel.add(startButton, BorderLayout.SOUTH);
        mainPanel.add(welcomePanel, "welcome");

        // شاشة تسجيل الدخول
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(224, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("اسم المستخدم:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField nameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("كلمة المرور:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("تسجيل الدخول");
        loginButton.setBackground(new Color(60, 179, 113));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(e -> {
            if (nameField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(this, "يرجى تعبئة اسم المستخدم وكلمة المرور.", "خطأ", JOptionPane.ERROR_MESSAGE);
            } else {
                cardLayout.show(mainPanel, "carSelection");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        mainPanel.add(loginPanel, "login");

        // شاشة اختيار السيارة + أزرار إضافية
        JPanel carSelectionPanel = new JPanel(new BorderLayout());
        carSelectionPanel.setBackground(new Color(255, 248, 220));

        JPanel brandGrid = new JPanel(new GridLayout(4, 2, 10, 10));
        for (String brand : carBrands) {
            JButton brandButton = new JButton(brand);
            brandButton.setFont(new Font("Arial", Font.BOLD, 16));
            brandButton.setBackground(new Color(70, 130, 180));
            brandButton.setForeground(Color.WHITE);
            brandButton.addActionListener(e -> showCarModels(brand));
            brandGrid.add(brandButton);
        }

        JPanel extraButtonsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton aboutUsButton = new JButton("عن الشركة");
        aboutUsButton.setFont(new Font("Arial", Font.BOLD, 16));
        aboutUsButton.setBackground(new Color(100, 149, 237));
        aboutUsButton.setForeground(Color.WHITE);
        aboutUsButton.addActionListener(e -> cardLayout.show(mainPanel, "about"));

        JButton reviewsButton = new JButton("آراء العملاء");
        reviewsButton.setFont(new Font("Arial", Font.BOLD, 16));
        reviewsButton.setBackground(new Color(46, 139, 87));
        reviewsButton.setForeground(Color.WHITE);
        reviewsButton.addActionListener(e -> cardLayout.show(mainPanel, "reviews"));

        extraButtonsPanel.add(aboutUsButton);
        extraButtonsPanel.add(reviewsButton);

        carSelectionPanel.add(brandGrid, BorderLayout.CENTER);
        carSelectionPanel.add(extraButtonsPanel, BorderLayout.SOUTH);
        carSelectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(carSelectionPanel, "carSelection");

        // واجهة "عن الشركة"
        JPanel aboutPanel = new JPanel(new BorderLayout());
        aboutPanel.setBackground(new Color(230, 230, 250));
        JLabel aboutLabel = new JLabel("<html><div style='text-align:center;'>نحن شركة متخصصة في بيع السيارات الحديثة والمستعملة<br>نعمل على توفير أفضل الأسعار وأجود الأنواع<br>هدفنا رضا العميل وثقته</div></html>", JLabel.CENTER);
        aboutLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        aboutPanel.add(aboutLabel, BorderLayout.CENTER);

        JButton backAbout = new JButton("عودة");
        backAbout.addActionListener(e -> cardLayout.show(mainPanel, "carSelection"));
        aboutPanel.add(backAbout, BorderLayout.SOUTH);
        mainPanel.add(aboutPanel, "about");

        // واجهة "آراء العملاء"
        JPanel reviewsPanel = new JPanel(new BorderLayout());
        reviewsPanel.setBackground(new Color(245, 255, 250));
        JLabel reviewsLabel = new JLabel("<html><div style='text-align:right;'>- عمرو العريقي: خدمة رائعة وسريعة!<br>- فراس  القدسي: وجدت السيارة التي أبحث عنها بسهولة<br>- هاشم الكحلاني: أنصح الجميع بالتعامل معهم</div></html>", JLabel.CENTER);
        reviewsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        reviewsPanel.add(reviewsLabel, BorderLayout.CENTER);

        JButton backReviews = new JButton("عودة");
        backReviews.addActionListener(e -> cardLayout.show(mainPanel, "carSelection"));
        reviewsPanel.add(backReviews, BorderLayout.SOUTH);
        mainPanel.add(reviewsPanel, "reviews");

        add(mainPanel);
        cardLayout.show(mainPanel, "welcome");
    }

    private void initializeCarData() {
        for (String brand : carBrands) {
            String[] models = {
                brand + " Model A", brand + " Model B", brand + " Model C",
                brand + " Model D", brand + " Model E"
            };
            carModels.put(brand, models);

            String[] details = {
                "الإصدار: " + brand + " Model A\nالسعر: 20000 $\nاللون: أحمر\nتاريخ الإصدار: 2020\nالكيلومترات المقطوعة: 50,000 كم",
                "الإصدار: " + brand + " Model B\nالسعر: 25000 $\nاللون: أزرق\nتاريخ الإصدار: 2021\nالكيلومترات المقطوعة: 30,000 كم",
                "الإصدار: " + brand + " Model C\nالسعر: 30000 $\nاللون: أسود\nتاريخ الإصدار: 2022\nالكيلومترات المقطوعة: 20,000 كم",
                "الإصدار: " + brand + " Model D\nالسعر: 35000 $\nاللون: أبيض\nتاريخ الإصدار: 2023\nالكيلومترات المقطوعة: 10,000 كم",
                "الإصدار: " + brand + " Model E\nالسعر: 40000 $\nاللون: فضي\nتاريخ الإصدار: 2024\nالكيلومترات المقطوعة: 5,000 كم"
            };
            carDetails.put(brand, details);
        }
    }

    private void showCarModels(String brand) {
        JPanel modelPanel = new JPanel(new BorderLayout());
        modelPanel.setBackground(new Color(240, 248, 255));
        modelPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("مرحباً بك في عالم " + brand, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        modelPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel modelsGrid = new JPanel(new GridLayout(0, 1, 10, 10));
        modelsGrid.setBackground(Color.WHITE);

        String[] models = carModels.get(brand);
        String[] details = carDetails.get(brand);

        for (int i = 0; i < models.length; i++) {
            JButton modelButton = new JButton(models[i]);
            modelButton.setFont(new Font("Arial", Font.PLAIN, 16));
            modelButton.setBackground(new Color(0, 123, 255));
            modelButton.setForeground(Color.WHITE);
            String detail = details[i];
            String model = models[i];
            modelButton.addActionListener(e -> showCarDetails(model, detail));
            modelsGrid.add(modelButton);
        }

        modelPanel.add(modelsGrid, BorderLayout.CENTER);

        JButton backButton = new JButton("عودة");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "carSelection"));
        modelPanel.add(backButton, BorderLayout.SOUTH);

        mainPanel.add(modelPanel, brand);
        cardLayout.show(mainPanel, brand);
    }

    private void showCarDetails(String model, String detail) {
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBackground(Color.WHITE);

        JLabel detailsLabel = new JLabel("<html><div style='text-align:right;'>" + detail.replace("\n", "<br>") + "</div></html>", JLabel.CENTER);
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton backButton = new JButton("عودة");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "carSelection"));

        JButton buyButton = new JButton("طلب شراء");
        buyButton.setBackground(new Color(34, 139, 34));
        buyButton.setForeground(Color.WHITE);
        buyButton.addActionListener(e -> showPurchaseForm(model));

        buttonPanel.add(backButton);
        buttonPanel.add(buyButton);

        JLabel contactLabel = new JLabel("للتواصل: 776184790", JLabel.CENTER);
        contactLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contactLabel.setForeground(new Color(25, 25, 112));

        detailsPanel.add(detailsLabel, BorderLayout.CENTER);
        detailsPanel.add(buttonPanel, BorderLayout.SOUTH);
        detailsPanel.add(contactLabel, BorderLayout.NORTH);

        mainPanel.add(detailsPanel, model);
        cardLayout.show(mainPanel, model);
    }

    private void showPurchaseForm(String model) {
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("اسمك الكامل:"));
        panel.add(nameField);
        panel.add(new JLabel("رقم التواصل:"));
        panel.add(phoneField);
        panel.add(new JLabel("موديل السيارة:"));
        panel.add(new JLabel(model));

        int result = JOptionPane.showConfirmDialog(this, panel, "طلب شراء السيارة", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, "تم إرسال طلبك بنجاح!\nسنتواصل معك قريباً.", "شكراً لك", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarnewSuppliersystem frame = new CarnewSuppliersystem();
            frame.setVisible(true);
        });
    }
}