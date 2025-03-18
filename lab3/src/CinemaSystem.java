import java.util.*;

class Cinema {
    String name;
    List<Hall> halls;

    Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    void addHall(Hall hall) {
        halls.add(hall);
    }
}

class Hall {
    int number;
    Seat[][] seats;

    Hall(int number, int rows, int cols) {
        this.number = number;
        this.seats = new Seat[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }
}

class Seat {
    int row;
    int col;
    boolean isOccupied;

    Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.isOccupied = false;
    }
}

class Movie {
    String title;
    int duration;

    Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
}

class Session {
    Movie movie;
    Hall hall;
    Date time;

    Session(Movie movie, Hall hall, Date time) {
        this.movie = movie;
        this.hall = hall;
        this.time = time;
    }
}

class Ticket {
    Session session;
    Seat seat;

    Ticket(Session session, Seat seat) {
        this.session = session;
        this.seat = seat;
    }
}

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Admin extends User {
    Admin(String username, String password) {
        super(username, password);
    }
}

public class CinemaSystem {
    private static List<Cinema> cinemas = new ArrayList<>();
    private static List<Movie> movies = new ArrayList<>();
    private static List<Session> sessions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Пример данных для тестирования
        User user = new User("user", "user123");
        Admin admin = new Admin("admin", "admin123");

        System.out.println("Введите логин:");
        String username = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        if (username.equals(user.username) && password.equals(user.password)) {
            userMenu();
        } else if (username.equals(admin.username) && password.equals(admin.password)) {
            adminMenu();
        } else {
            System.out.println("Неверный логин или пароль.");
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("1. Поиск ближайшего сеанса");
            System.out.println("2. Купить билет");
            System.out.println("3. Выход");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    findNearestSession();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("1. Добавить кинотеатр");
            System.out.println("2. Добавить зал");
            System.out.println("3. Создать сеанс");
            System.out.println("4. Выход");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addCinema();
                    break;
                case 2:
                    addHall();
                    break;
                case 3:
                    createSession();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void addCinema() {
        System.out.println("Введите название кинотеатра:");
        String name = scanner.next();
        cinemas.add(new Cinema(name));
    }

    private static void addHall() {
        System.out.println("Введите номер кинотеатра:");
        int cinemaIndex = scanner.nextInt();
        System.out.println("Введите номер зала:");
        int hallNumber = scanner.nextInt();
        System.out.println("Введите количество рядов:");
        int rows = scanner.nextInt();
        System.out.println("Введите количество мест в ряду:");
        int cols = scanner.nextInt();
        cinemas.get(cinemaIndex).addHall(new Hall(hallNumber, rows, cols));
    }

    private static void createSession() {
        System.out.println("Введите номер кинотеатра:");
        int cinemaIndex = scanner.nextInt();
        System.out.println("Введите номер зала:");
        int hallIndex = scanner.nextInt();
        System.out.println("Введите название фильма:");
        String title = scanner.next();
        System.out.println("Введите длительность фильма:");
        int duration = scanner.nextInt();
        System.out.println("Введите время сеанса (в формате ГГГГ-ММ-ДД ЧЧ:ММ):");
        String timeString = scanner.next();
        Date time = new Date(timeString); // Здесь нужно преобразовать строку в дату
        Movie movie = new Movie(title, duration);
        sessions.add(new Session(movie, cinemas.get(cinemaIndex).halls.get(hallIndex), time));
    }

    private static void findNearestSession() {
        System.out.println("Введите название фильма:");
        String title = scanner.next();
        Date now = new Date();
        Session nearestSession = null;
        for (Session session : sessions) {
            if (session.movie.title.equals(title) && session.time.after(now)) {
                if (nearestSession == null || session.time.before(nearestSession.time)) {
                    nearestSession = session;
                }
            }
        }
        if (nearestSession != null) {
            System.out.println("Ближайший сеанс: " + nearestSession.time);
        } else {
            System.out.println("Сеансов не найдено.");
        }
    }

    private static void buyTicket() {
        System.out.println("Введите номер сеанса:");
        int sessionIndex = scanner.nextInt();
        Session session = sessions.get(sessionIndex);
        System.out.println("Введите номер ряда:");
        int row = scanner.nextInt();
        System.out.println("Введите номер места:");
        int col = scanner.nextInt();
        if (!session.hall.seats[row][col].isOccupied) {
            session.hall.seats[row][col].isOccupied = true;
            System.out.println("Билет куплен.");
        } else {
            System.out.println("Место уже занято.");
        }
    }
}