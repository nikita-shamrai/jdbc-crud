package DBUtil;

public enum JdbcConst {
    USERNAME("root"),
    PASSWORD("1234"),
    URL("jdbc:mysql://localhost:3306/test");

    private final String value;
    JdbcConst(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
