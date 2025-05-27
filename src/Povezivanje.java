import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Povezivanje {
    private static DataSource createDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("localhost");
        //ds.setPortNumber(1433);
        ds.setDatabaseName("JavaAdv");
        ds.setUser("sa");
        ds.setPassword("SQL");
        ds.setEncrypt(false);
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return createDataSource().getConnection();
    }

    public void unesiPolaznika(int PolaznikID, String ime, String prezime) throws SQLException {
        String sql = "INSERT INTO PolazniK ( Ime, Prezime) VALUES (?, ?, ?)";
        try (Connection conn = Povezivanje.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, PolaznikID);
            ps.setString(2, ime);
            ps.setString(3, prezime);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unesiProgram(int programobrazovanjaid, String naziv, int csvet) throws SQLException {
        String sql = "INSERT INTO ProgramObrazovanja (ProgramObrazovanjaID, Naziv, CSVET) VALUES (?, ?, ?)";
        try (Connection conn = Povezivanje.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, programobrazovanjaid);
            ps.setString(2, naziv);
            ps.setInt(3, csvet);
            ps.executeUpdate();
        }
    }

    public void upisiPolaznikaNaProgram(int UpisID, int idPolaznik, int idProgram) throws SQLException {
        String sql = "INSERT INTO Upis (UpisID, IDPolaznik, IDProgramObrazovanja) VALUES (?, ?, ?)";
        try (Connection conn = Povezivanje.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, UpisID);
            ps.setInt(2, idPolaznik);
            ps.setInt(3, idProgram);
            ps.executeUpdate();
        }
    }

    public void prebaciPolaznika(int idPolaznik, int stariProgramID, int noviProgramID) throws SQLException {
        String sql = "UPDATE Upis SET IDProgramObrazovanja = ? WHERE IDPolaznik = ? AND IDProgramObrazovanja = ?";
        try (Connection conn = Povezivanje.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, noviProgramID);
            ps.setInt(2, idPolaznik);
            ps.setInt(3, stariProgramID);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                System.out.println("Nije pronađen upis za ovog polaznika i program.");
            }
        }
    }

    public void ispisiPolaznikeZaProgram(int idProgram) throws SQLException {
        String sql = "SELECT p.Ime, p.Prezime, pr.Naziv, pr.CSVET " +
                "FROM Polaznik p " +
                "JOIN Upis u ON p.PolaznikID = u.IDPolaznik " +
                "JOIN ProgramObrazovanja pr ON u.IDProgramObrazovanja = pr.ProgramObrazovanjaID " +
                "WHERE pr.ProgramObrazovanjaID = ?";
        try (Connection conn = Povezivanje.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProgram);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("Ime: %s, Prezime: %s, Program: %s, CSVET: %d%n",
                            rs.getString("Ime"),
                            rs.getString("Prezime"),
                            rs.getString("Naziv"),
                            rs.getInt("CSVET"));
                }
            }
        }
    }
}


