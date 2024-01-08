package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernateConnection {
    public static void main(String[] args) {
        // Khởi tạo SessionFactory từ cấu hình
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Mở một phiên làm việc (session)
        try (Session session = sessionFactory.openSession()) {
            // Nếu không có exception, đồng nghĩa kết nối thành công
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
        } catch (Exception e) {
            // Nếu có exception, đồng nghĩa kết nối không thành công
            System.err.println("Không thể kết nối đến cơ sở dữ liệu: " + e.getMessage());
        } finally {
            // Đóng SessionFactory khi không cần sử dụng nữa
            sessionFactory.close();
        }
    }
}
