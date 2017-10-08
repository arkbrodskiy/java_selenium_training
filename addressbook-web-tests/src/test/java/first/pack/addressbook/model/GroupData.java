package first.pack.addressbook.model;

public class GroupData {
    private final String value;
    private final String name;
    private final String header;
    private final String footer;

    public GroupData(String value, String name, String header, String footer) {
        this.value = value;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public GroupData(String name, String header, String footer) {
        this.value = null;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (value != null ? !value.equals(groupData.value) : groupData.value != null) return false;
        return name != null ? name.equals(groupData.name) : groupData.name == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
