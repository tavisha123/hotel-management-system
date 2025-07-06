import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNo;
    private String bedType; // "Single" or "Double"
    private int occupancy;
    private String aadhaarCard;
    private boolean breakfastIncluded;
    private boolean cleaned;
    private boolean available;
    private String category; // "Deluxe" or "Normal"
    private double price;

    public Room(int roomNo, String bedType, int occupancy, String aadhaarCard,
                boolean breakfastIncluded, boolean cleaned, boolean available,
                String category, double price) {
        this.roomNo = roomNo;
        this.bedType = bedType;
        this.occupancy = occupancy;
        this.aadhaarCard = aadhaarCard;
        this.breakfastIncluded = breakfastIncluded;
        this.cleaned = cleaned;
        this.available = available;
        this.category = category;
        this.price = price;
    }

    public int getRoomNo() { return roomNo; }
    public String getBedType() { return bedType; }
    public int getOccupancy() { return occupancy; }
    public String getAadhaarCard() { return aadhaarCard; }
    public boolean isBreakfastIncluded() { return breakfastIncluded; }
    public boolean isCleaned() { return cleaned; }
    public boolean isAvailable() { return available; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    public void setBedType(String bedType) { this.bedType = bedType; }
    public void setOccupancy(int occupancy) { this.occupancy = occupancy; }
    public void setAadhaarCard(String aadhaarCard) { this.aadhaarCard = aadhaarCard; }
    public void setBreakfastIncluded(boolean breakfastIncluded) { this.breakfastIncluded = breakfastIncluded; }
    public void setCleaned(boolean cleaned) { this.cleaned = cleaned; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
}

public class HotelRoomCRUD {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Room> rooms = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nHotel Room Management");
            System.out.println("1. Add Room");
            System.out.println("2. Update Room");
            System.out.println("3. Delete Room");
            System.out.println("4. View Table");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1: addRoom(); break;
                case 2: updateRoom(); break;
                case 3: deleteRoom(); break;
                case 4: printRoomTable(); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid option!");
            }
            printRoomTable(); // Always print the updated table after each operation
        }
    }

    private static void addRoom() {
        System.out.print("Enter Room Number: ");
        int roomNo = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Bed Type (Single/Double): ");
        String bedType = scanner.nextLine();
        System.out.print("Enter Occupancy: ");
        int occupancy = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Guest Aadhaar Card Number: ");
        String aadhaarCard = scanner.nextLine();
        System.out.print("Is Breakfast Included? (yes/no): ");
        boolean breakfastIncluded = scanner.nextLine().trim().equalsIgnoreCase("yes");
        System.out.print("Is Room Cleaned? (yes/no): ");
        boolean cleaned = scanner.nextLine().trim().equalsIgnoreCase("yes");
        System.out.print("Is Room Available? (yes/no): ");
        boolean available = scanner.nextLine().trim().equalsIgnoreCase("yes");
        System.out.print("Enter Category (Deluxe/Normal): ");
        String category = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        rooms.add(new Room(roomNo, bedType, occupancy, aadhaarCard, breakfastIncluded, cleaned, available, category, price));
        System.out.println("Room added successfully.");
    }

    private static void updateRoom() {
        System.out.print("Enter Room Number to update: ");
        int roomNo = Integer.parseInt(scanner.nextLine());
        Room room = findRoomByNo(roomNo);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }
        System.out.print("Enter new Bed Type (leave blank to keep unchanged): ");
        String bedType = scanner.nextLine();
        if (!bedType.isEmpty()) room.setBedType(bedType);

        System.out.print("Enter new Occupancy (leave blank to keep unchanged): ");
        String occupancyStr = scanner.nextLine();
        if (!occupancyStr.isEmpty()) room.setOccupancy(Integer.parseInt(occupancyStr));

        System.out.print("Enter new Aadhaar Card (leave blank to keep unchanged): ");
        String aadhaarCard = scanner.nextLine();
        if (!aadhaarCard.isEmpty()) room.setAadhaarCard(aadhaarCard);

        System.out.print("Is Breakfast Included? (yes/no/blank to keep): ");
        String breakfast = scanner.nextLine();
        if (breakfast.equalsIgnoreCase("yes")) room.setBreakfastIncluded(true);
        else if (breakfast.equalsIgnoreCase("no")) room.setBreakfastIncluded(false);

        System.out.print("Is Room Cleaned? (yes/no/blank to keep): ");
        String cleaned = scanner.nextLine();
        if (cleaned.equalsIgnoreCase("yes")) room.setCleaned(true);
        else if (cleaned.equalsIgnoreCase("no")) room.setCleaned(false);

        System.out.print("Is Room Available? (yes/no/blank to keep): ");
        String available = scanner.nextLine();
        if (available.equalsIgnoreCase("yes")) room.setAvailable(true);
        else if (available.equalsIgnoreCase("no")) room.setAvailable(false);

        System.out.print("Enter new Category (leave blank to keep unchanged): ");
        String category = scanner.nextLine();
        if (!category.isEmpty()) room.setCategory(category);

        System.out.print("Enter new Price (leave blank to keep unchanged): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isEmpty()) room.setPrice(Double.parseDouble(priceStr));

        System.out.println("Room updated successfully.");
    }

    private static void deleteRoom() {
        System.out.print("Enter Room Number to delete: ");
        int roomNo = Integer.parseInt(scanner.nextLine());
        Room room = findRoomByNo(roomNo);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }
        rooms.remove(room);
        System.out.println("Room deleted successfully.");
    }

    private static Room findRoomByNo(int roomNo) {
        for (Room room : rooms) {
            if (room.getRoomNo() == roomNo) return room;
        }
        return null;
    }

    private static void printRoomTable() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "RoomNo", "BedType", "Occupancy", "Aadhaar", "Breakfast", "Cleaned", "Available", "Category", "Price");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Room room : rooms) {
            System.out.printf("%-8d %-8s %-10d %-16s %-10s %-10s %-12s %-10s %-10.2f%n",
                    room.getRoomNo(),
                    room.getBedType(),
                    room.getOccupancy(),
                    room.getAadhaarCard(),
                    room.isBreakfastIncluded() ? "Yes" : "No",
                    room.isCleaned() ? "Yes" : "No",
                    room.isAvailable() ? "Yes" : "No",
                    room.getCategory(),
                    room.getPrice());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}