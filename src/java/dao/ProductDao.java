/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import common.DBUtils;
import entities.Product;
import entities.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;

    public int createProduct(Product product) throws SQLException {
        int productId = -1; // Default value if the product creation fails

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO product (product_code, product_status, product_origin, product_category, product_type, product_name, description, image, sale) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, product.getProductCode());
            pstmt.setBoolean(2, product.isProductStatus());
            pstmt.setInt(3, product.getProductOrigin());
            pstmt.setInt(4, product.getProductCategory());
            pstmt.setString(5, product.getType());
            pstmt.setString(6, product.getProductName());
            pstmt.setString(7, product.getDescription());
            pstmt.setString(8, product.getImage());
            pstmt.setFloat(9, product.getSale());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys (including the productId)
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    productId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return productId;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM product";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType, productName, description, image, sale);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    public boolean findProductId(int productId) throws SQLException {
        boolean exists = false;
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT 1 FROM product WHERE product_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();
            exists = rs.next(); // If a row is found, the product exists
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return exists;
    }

    public List<Product> getFourProductsWithLargestSale() throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT TOP 4 * FROM product ORDER BY sale DESC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType, productName, description, image, sale);

                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    public List<Product> getFourBestSellingProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT TOP 4 p.*, SUM(od.quantity) AS total_quantity FROM product p "
                    + "LEFT JOIN order_details od ON p.product_id = od.product_id "
                    + "GROUP BY p.product_id, p.product_code, p.product_status, p.product_origin, p.product_type, "
                    + "p.product_name, p.description, p.image, p.sale, p.product_category "
                    + "ORDER BY total_quantity DESC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType, productName, description, image, sale);

                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If there are fewer than 4 best-selling products, randomly select 4 products
        if (products.size() < 4) {
            List<Product> randomProducts = getRandomProducts(4 - products.size());
            products.addAll(randomProducts);
        }

        return products;
    }
    
    public List<Product> getSixBestSellingProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT TOP 6 p.*, SUM(od.quantity) AS total_quantity FROM product p "
                    + "LEFT JOIN order_details od ON p.product_id = od.product_id "
                    + "GROUP BY p.product_id, p.product_code, p.product_status, p.product_origin, p.product_type, "
                    + "p.product_name, p.description, p.image, p.sale, p.product_category "
                    + "ORDER BY total_quantity DESC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType, productName, description, image, sale);

                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (products.size() < 6) {
            List<Product> randomProducts = getRandomProducts(6 - products.size());
            products.addAll(randomProducts);
        }

        return products;
    }

    private List<Product> getRandomProducts(int count) throws SQLException {
        List<Product> randomProducts = new ArrayList<>();

        try {
            sql = "SELECT TOP " + count + " * FROM product ORDER BY NEWID()";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType, productName, description, image, sale);
                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                randomProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return randomProducts;
    }

    public List<Product> getAllProductByTypePagin(String type, int currentPage, int itemsPerPage) throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            StringBuilder sb = new StringBuilder("SELECT * FROM product");

            if (type != null) {
                sb.append(" WHERE product_type = ?");
            }

            sb.append(" ORDER BY product_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

            sql = sb.toString();
            pstmt = con.prepareStatement(sql);

            int parameterIndex = 1;

            if (type != null) {
                pstmt.setString(parameterIndex++, type);
            }

            int offset = (currentPage - 1) * itemsPerPage;

            pstmt.setInt(parameterIndex++, offset);
            pstmt.setInt(parameterIndex++, itemsPerPage);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType,
                        productName, description, image, sale);
                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    public List<Product> getAllProductByType(String type) throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            StringBuilder sb = new StringBuilder("SELECT * FROM product");

            if (type != null) {
                sb.append(" WHERE product_type = ?");
            }

            sql = sb.toString();
            pstmt = con.prepareStatement(sql);

            if (type != null) {
                pstmt.setString(1, type);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType,
                        productName, description, image, sale);
                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    public List<Product> getProductByOriginId(int originId) throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM product WHERE product_origin = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, originId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType,
                        productName, description, image, sale);
                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    public List<Product> getProductByCategoryId(int categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM product WHERE product_category = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType,
                        productName, description, image, sale);
                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    public List<Product> getAllProductByPriceRange(float minPrice, float maxPrice) throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM product";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                SizeDao sizeDao = new SizeDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                Product product = new Product(productId, productCode, productStatus, productOrigin, productType,
                        productName, description, image, sale);
                product.setSizes(sizes);
                float smallestPrice = getSmallestSizePrice(sizes);
                float price = smallestPrice - smallestPrice * sale / 100;
                if (isPriceInRange(price, minPrice, maxPrice)) {
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    private float getSmallestSizePrice(List<Size> sizes) {
        float smallestPrice = Float.MAX_VALUE;
        for (Size size : sizes) {
            if (size.getPrice() < smallestPrice) {
                smallestPrice = size.getPrice();
            }
        }
        return smallestPrice;
    }

    private boolean isPriceInRange(float price, float minPrice, float maxPrice) {
        return (minPrice <= price) && (price <= maxPrice);
    }

    public List<Product> searchProductByName(String name) throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM product WHERE product_name LIKE ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType,
                        productName, description, image, sale);
                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    public List<Product> getAllProductSortedByPrice(boolean ascendingOrder) throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT p.product_id, p.product_code, p.product_status, p.product_origin, p.product_type, p.product_name, p.product_category, p.description, p.image, p.sale, MIN(s.price - s.price * p.sale / 100) AS min_price\n"
                    + "FROM product p \n"
                    + "JOIN size s \n"
                    + "ON p.product_id = s.product_id\n"
                    + "GROUP BY p.product_id, p.product_code, p.product_status, p.product_origin, p.product_type, p.product_name, p.product_category, p.description, p.image, p.sale\n"
                    + "ORDER BY min_price " + (ascendingOrder ? "ASC" : "DESC");
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType,
                        productName, description, image, sale);
                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    public List<Product> getAllProductSortedByName(boolean ascendingOrder) throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT p.product_id, p.product_code, p.product_status, p.product_origin, p.product_type, p.product_name, p.product_category, p.description, p.image, p.sale "
                    + "FROM product p "
                    + "ORDER BY p.product_name " + (ascendingOrder ? "ASC" : "DESC");
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType,
                        productName, description, image, sale);
                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return products;
    }

    public List<Product> getAllBestSellingProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT p.*, SUM(od.quantity) AS total_quantity FROM product p "
                    + "LEFT JOIN order_details od ON p.product_id = od.product_id "
                    + "GROUP BY p.product_id, p.product_code, p.product_status, p.product_origin, p.product_type, "
                    + "p.product_name, p.description, p.image, p.sale, p.product_category "
                    + "ORDER BY total_quantity DESC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                Product product = new Product(productId, productCode, productStatus, productOrigin, productType, productName, description, image, sale);

                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public Product getProductByProductId(int productId) throws SQLException {
        Product product = null;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM product WHERE product_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String productCode = rs.getString("product_code");
                boolean productStatus = rs.getBoolean("product_status");
                int productOrigin = rs.getInt("product_origin");
                String productType = rs.getString("product_type");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float sale = rs.getFloat("sale");

                product = new Product(productId, productCode, productStatus, productOrigin, productType, productName, description, image, sale);

                SizeDao sizeDao = new SizeDao();
                OriginDao originDao = new OriginDao();
                List<Size> sizes = sizeDao.getAllSizes(productId);
                sizes.sort(Comparator.comparingDouble(Size::getPrice));
                product.setSizes(sizes);
                product.setOrigin(originDao.getOriginById(productOrigin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public boolean checkProductIdExist(int productId) throws SQLException {
        boolean exists = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT COUNT(*) AS count FROM product WHERE product_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                exists = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return exists;
    }

}
