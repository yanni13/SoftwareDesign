import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class main {

    public static void main(String args[]) {
        int count = 1;//사용자 수
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("안녕하세요! 오늘 뭐먹지 키오스크를 이용해 주셔서 감사합니다.");
            System.out.println("원하시는 작업의 번호를 입력해주세요.");
            System.out.println(" ");
            System.out.println("관리자/ 1.로그인 2.로그아웃 3.추천메뉴 등록 4.재고 정보 5.주문요청");
            System.out.println("사용자/ 6.메뉴정보 7.음식주문 8.결제하기 9.폰트 크기 조절 10.음성안내 11.추천메뉴 12. 시스템 종료");
            int n = sc.nextInt();
            System.out.println("------------------------------------------------------------------");

            if(n == 12) {//시스템 종료
                break;
            }

            if(n==1) {//로그인
                System.out.println("=====================");
                System.out.println("<<로그인>>");
                System.out.print("ID : ");
                int id = sc.nextInt();
                System.out.print("Passwd : ");
                int pw = sc.nextInt();
    
                Login login = new Login();
                login.execute(id,pw);
            }
            
            if(n==2) {//로그아웃
                Logout logout = new Logout();
                logout.execute();
            } 
    
            if(n==3) {//추천메뉴 등록
                //System.out.println("추천메뉴 등록을 실행합니다.");
                addRecommendedMenu recommendMenuManager = new addRecommendedMenu();
                recommendMenuManager.execute(sc);
            }
    
            if(n==4) {//재고 정보
                Stock stockInfo = new Stock();
                stockInfo.StockInto();
            }
    
            if(n==5) {//주문 요청
                OrderRequest order = new OrderRequest();
            }
    
            if(n==6) {//메뉴정보
                System.out.println("=====================================================================");
                System.out.println("<<메뉴정보>>");
                System.out.println("분식"+"\t\t"+"면"+"\t\t"+"덮밥"+"\t\t"+"음료");
                System.out.println("---------------------------------------------------------------------");
                System.out.println("김밥(3500)"+"\t"+"해물라면(4500)"+"\t"+"제육덮밥(5000)"+"\t"+"사이다(2000)");
                System.out.println("떡볶이(4000)"+"\t"+"얼큰우동(4000)"+"\t"+"참치마요(4500)"+"\t"+"콜라(2000)");
                System.out.println("순대(3000)"+"\t"+"김치라면(4000)"+"\t"+"치킨마요(4500)"+"\t"+"한라봉주스(3000)");
                System.out.println("=====================================================================");
            }
    
            if(n==7) {//주문하기
                Order order = new Order();
                order.placeOrder();
            }
    
            if(n==8) {//결제요청, 결제확인
                Order order = new Order();
                Payment payment = new Payment();

                int totalOrderPrice = order.getTotalOrderPrice();
                payment.requestPayment(Order.getCount(), order.getOrderedMenus(), totalOrderPrice);
                break;
            }
            
            
            if(n==9) {//폰트 기능
                Font font = new Font();
                System.out.println("=====================================================================");
                System.out.println("<<폰트 크기 조절하기>>");
                System.out.print("보통(1)  크게(2)  더 크게(3) >>");
                int fontSize = sc.nextInt();
                font.adjustFontSize(fontSize);
            }
    
            if(n==10) {//음성안내
                VoiceGuide voiceGuide = new VoiceGuide();
                voiceGuide.showVoiceGuide();
            }
    
            if(n==11) {//추천기능
                Recommend_Menu recommendMenu = new Recommend_Menu();
                Recommend_Menu.execute(sc);
            }
        }
        sc.close();
   
    } 

}

class Login {
    public void execute(int id, int pw) {
        if((id == 1234) && (pw ==1234)) {
            System.out.println("=====================================================================");
            System.out.println("로그인 완료 되셨습니다.");
        } else {
            System.out.println("");
            System.out.println("로그인에 실패하셨습니다. 다시 시도해주세요.");
        }
    }
}

class Logout {
    public void execute() {
        System.out.println("=====================================================================");
        System.out.println("로그아웃 되셨습니다.");
        System.out.println("시스템 실행을 종료합니다.");
        System.out.println("=====================================================================");
        System.exit(0); // 시스템 종료
    }
}

class addRecommendedMenu {
    private static String registeredMenu;

    public static void execute(Scanner scanner) {
        String str ="";
        System.out.println("=====================================================================");
        System.out.println("<<추천메뉴 등록>>");
        System.out.print("오늘의 추천 메뉴: ");
        str = scanner.next();
        scanner.nextLine();//개행문자 제거
        registeredMenu = str;
        System.out.println("오늘의 추천메뉴는 " + registeredMenu +"입니다.");
        System.out.println("=====================================================================");
        System.out.println("등록이 완료 되셨습니다.");
    }
    public static String getRegisteredMenu() {
        return registeredMenu;
    }
}

class RecommendMenu {
    private static String registeredMenu;

    public static void setRegisteredMenu(String menu) {
        registeredMenu = menu;
    }

    public static String getRegisteredMenu() {
        return registeredMenu;
    }
}

class Recommend_Menu {
    private static RecommendMenu recommendMenu = new RecommendMenu();

    public static void execute(Scanner scanner) {
        System.out.println("=====================================================================");
        System.out.println("<<오늘의 추천메뉴>>");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            String formattedDate = now.format(formatter);

            String registeredMenu = addRecommendedMenu.getRegisteredMenu();
            System.out.println(formattedDate+"일의 추천메뉴는 " +registeredMenu+"입니다.");
            System.out.print("주문하시겠습니까?(y or n) :");
            String response = scanner.next();
            scanner.nextLine();
            System.out.println("저장되었습니다.");
            System.out.println("=====================================================================");
        }

}

class Stock {
    private Scanner sc;

    public Stock() {
        sc = new Scanner(System.in);
    }

    public void StockInto() {
        System.out.println("=====================================================================");
        System.out.println("<<재고 정보>>");
        System.out.print("현재 조리 불가능한 메뉴: ");
        String impossible = sc.next();
        sc.nextLine(); // 개행문자 제거
        System.out.println("현재 주문이 불가능한 음식은 " + impossible + "입니다.");

        System.out.print("재고가 부족한 재료들: ");
        String material = sc.nextLine();
        System.out.println(" ");
        System.out.println("재료 중 " + material + "이/가 부족합니다.");
        System.out.println("재료를 채워놔주세요.");

        System.out.println("=====================================================================");
    }
    
}

class Order {
    private static int count = 1;
    private List<String> orderedMenus;
    private List<Integer> orderedPrices;

    public Order() {
        orderedMenus = new ArrayList<>();
        orderedPrices = new ArrayList<>();
    }

    public List<String> getOrderedMenus() {
        return null;
    }

    public int getTotalOrderPrice() {
        return 0;
    }

    public static int getCount() {
        return count;
    }

    public static void increaseCount() {
        count++;
    }

    public void placeOrder() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=====================================================================");
        System.out.println("<<주문하기>>");
        System.out.println("분식"+"\t\t"+"면"+"\t\t\t"+"덮밥"+"\t\t\t"+"음료");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("김밥(3500)[1]"+"\t"+"해물라면(4500)[4]"+"\t"+"제육덮밥(5000)[7]"+"\t"+"사이다(2000)[10]");
        System.out.println("떡볶이(4000)[2]"+"\t"+"얼큰우동(4000)[5]"+"\t"+"참치마요(4500)[8]"+"\t"+"콜라(2000)[11]");
        System.out.println("순대(3000)[3]"+"\t"+"김치라면(4000)[6]"+"\t"+"치킨마요(4500)[9]"+"\t"+"한라봉주스(3000)[12]");
        System.out.println(" ");
        System.out.print("주문하고자 하는 메뉴의 번호를 입력해주세요.");
        int num = sc.nextInt();
        sc.nextLine();

            switch (num) {
                case 1:
                    orderedMenus.add("김밥");
                    orderedPrices.add(3500);
                    break;
                case 2:
                    orderedMenus.add("떡볶이");
                    orderedPrices.add(4000);
                    break;
                case 3:
                    orderedMenus.add("순대");
                    orderedPrices.add(3000);
                    break;
                case 4:
                    orderedMenus.add("해물라면");
                    orderedPrices.add(4500);
                    break;
                case 5:
                    orderedMenus.add("얼큰우동");
                    orderedPrices.add(4000);
                    break;
                case 6:
                    orderedMenus.add("김치라면");
                    orderedPrices.add(4000);
                    break;
                case 7:
                    orderedMenus.add("제육덮밥");
                    orderedPrices.add(5000);
                    break;
                case 8:
                    orderedMenus.add("참치마요");
                    orderedPrices.add(4500);
                    break;
                case 9:
                    orderedMenus.add("치킨마요");
                    orderedPrices.add(4500);
                    break;
                case 10:
                    orderedMenus.add("사이다");
                    orderedPrices.add(2000);
                    break;
                case 11:
                    orderedMenus.add("콜라");
                    orderedPrices.add(2000);
                    break;
                case 12:
                    orderedMenus.add("한라봉주스");
                    orderedPrices.add(3000);
                    break;
                default:
                    System.out.println("알 수 없는 메뉴");
                    break;
            }
        System.out.println("주문이 저장 되었습니다.");
        System.out.println("---------------------------------------------------");
        System.out.print("결제 단계로 넘어가시겠습니까?(y or n)");
        String response = sc.nextLine();
        System.out.println("=====================================================================");

        if(response.equalsIgnoreCase("y")) {
            OrderRequest orderRequest = new OrderRequest();
            Payment payment = new Payment();
            int totalOrderPrice = orderedPrices.stream().mapToInt(Integer::intValue).sum();
            payment.requestPayment(count, orderedMenus, totalOrderPrice);
        }

        sc.close();
    }
    
        
}


class OrderRequest {
    public void sendOrderRequest(int count, String menu) {
        System.out.println("=====================================================================");
        System.out.println("<<주문요청>>");
        System.out.println("주문번호["+count+"] 의 주문내역 : "+ menu);
        System.out.println("=====================================================================");
    }
}

class Payment {
    private Scanner sc;

    public Payment() {
        sc = new Scanner(System.in);
    }

    public void requestPayment(int orderNumber, List<String> orderedMenu, int totalOrderPrice) {
        System.out.println("=====================================================================");
        System.out.println("<<결제요청>>");
            System.out.println("결제수단 : 삼성페이(1) 애플페이(2) 신용카드/체크카드(3)");
            System.out.println("총 지불금액 : " + totalOrderPrice +"원 입니다.");
            System.out.println("--------------------------------------------------");
            System.out.print("지불할 결제수단 :");
            int pay = sc.nextInt();
            sc.nextLine();
            System.out.print("결제요청 진행하시겠습니까?(y or n)");
            String response = sc.nextLine();
            System.out.println();
            System.out.println("결제 확인 단계로 넘어가겠습니다.");
            System.out.println();
            System.out.println("<<결제 확인>>");
            System.out.println("주문한 음식은 "+ orderedMenu + "이며  총" + totalOrderPrice +"원입니다.");
            System.out.print("결제하시겠습니까?(y or n)");
            String check = sc.nextLine();

            if(check.equalsIgnoreCase("y")) {
                System.out.println("결제가 완료되었습니다.");
            }
            
            System.out.println("주문번호 : "+orderNumber);
            System.out.println("=====================================================================");
        }
}

class Font {
    public void adjustFontSize(int fontSize) {

        switch(fontSize) {
            case 1:
                System.out.println("보통 크기로 변경되었습니다.");
                break;
            case 2:
                System.out.println("크게로 변경되었습니다.");
                break;
            case 3:
                System.out.println("더 크게로 변경되었습니다");
                break;
            default:
                System.out.println("잘못된 입력입니다. 폰트 크기 변경에 실패했습니다.");
                break;
            }
            System.out.println("=====================================================================");
    }

}

class VoiceGuide {
    public void showVoiceGuide() {
        System.out.println("=====================================================================");
        System.out.println("<<음성안내>>");
        System.out.println("음성으로 주문을 도와드리겠습니다.");
        System.out.println("=====================================================================");
    }
}
    
