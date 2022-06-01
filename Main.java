    import java.util.*;
    import java.io.*;
    import java.io.DataInputStream;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Scanner;
    import java.util.StringTokenizer;
    import java.math.BigInteger;
    
    public final class Main{
        static class Reader {
            final private int BUFFER_SIZE = 1 << 16;
            private DataInputStream din;
            private byte[] buffer;
            private int bufferPointer, bytesRead;
     
            public Reader() {
                din = new DataInputStream(System.in);
                buffer = new byte[BUFFER_SIZE];
                bufferPointer = bytesRead = 0;
            }
     
            public Reader(String file_name) throws IOException {
                din = new DataInputStream(
                    new FileInputStream(file_name));
                buffer = new byte[BUFFER_SIZE];
                bufferPointer = bytesRead = 0;
            }
     
            public String readLine() throws IOException {
                byte[] buf = new byte[64]; // line length
                int cnt = 0, c;
                while ((c = read()) != -1) {
                    if (c == '\n') {
                        if (cnt != 0) {
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                    buf[cnt++] = (byte)c;
                }
                return new String(buf, 0, cnt);
            }
     
            public int nextInt() throws IOException {
                int ret = 0;
                byte c = read();
                while (c <= ' ') {
                    c = read();
                }
                boolean neg = (c == '-');
                if (neg)
                    c = read();
                do {
                    ret = ret * 10 + c - '0';
                } while ((c = read()) >= '0' && c <= '9');
     
                if (neg)
                    return -ret;
                return ret;
            }
     
            public long nextLong() throws IOException {
                long ret = 0;
                byte c = read();
                while (c <= ' ')
                    c = read();
                boolean neg = (c == '-');
                if (neg)
                    c = read();
                do {
                    ret = ret * 10 + c - '0';
                } while ((c = read()) >= '0' && c <= '9');
                if (neg)
                    return -ret;
                return ret;
            }
     
            public double nextDouble() throws IOException {
                double ret = 0, div = 1;
                byte c = read();
                while (c <= ' ') c = read();
                boolean neg = (c == '-');
                if (neg) c = read();
     
                do {
                    ret = ret * 10 + c - '0';
                } while ((c = read()) >= '0' && c <= '9');
                if (c == '.') {
                    while ((c = read()) >= '0' && c <= '9') {
                        ret += (c - '0') / (div *= 10);
                    }
                }
                if (neg) return -ret;
                return ret;
            }
     
            private void fillBuffer() throws IOException {
                bytesRead = din.read(buffer, bufferPointer = 0,
                                     BUFFER_SIZE);
                if (bytesRead == -1)
                    buffer[0] = -1;
            }
            private byte read() throws IOException {
                if (bufferPointer == bytesRead)
                    fillBuffer();
                return buffer[bufferPointer++];
            }
     
            public void close() throws IOException {
                if (din == null)
                    return;
                din.close();
            }
        }
        static class Kattio extends PrintWriter {
            private BufferedReader r;
            private StringTokenizer st;
        
            // standard input
            public Kattio() { this(System.in, System.out); }
            public Kattio(InputStream i, OutputStream o) {
                super(o);
                r = new BufferedReader(new InputStreamReader(i));
            }
            // USACO-style file input
            public Kattio(String problemName) throws IOException {
                super(new FileWriter(problemName + ".out"));
                r = new BufferedReader(new FileReader(problemName + ".in"));
            }
        
            // returns null if no more input
            public String next() {
                try {
                    while (st == null || !st.hasMoreTokens())
                        st = new StringTokenizer(r.readLine());
                    return st.nextToken();
                } catch (Exception e) { }
                return null;
            }
        
            public int nextInt() { return Integer.parseInt(next()); }
            public double nextDouble() { return Double.parseDouble(next()); }
            public long nextLong() { return Long.parseLong(next()); }
        }   
        
        static Kattio sc = new Kattio();
        static long  mod  = 998244353l;
        static PrintWriter out =new PrintWriter(System.out);
     
        //Heapify function to maintain heap property.
        public static void swap(int i,int j,int arr[]) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        public static void swap(int i,int j,long arr[]) {
            long temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        public static void swap(int i,int j,char arr[]) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        static String endl = "\n" , gap = " ";
        public static void main(String[] args)throws IOException {
            int t  = ri();
            // List<Integer> list  = new ArrayList<>();
            // int MAX = (int)4e4;
            // for(int i =1;i<=MAX;i++) {
            //     if(isPalindrome(i + "")) list.add(i);
            // }
            // // System.out.println(list);
            // long dp[] = new long[MAX +1];
            // dp[0] = 1;
            // long mod = (long)(1e9+7);
            // for(int x : list) {
            //     for(int i =1;i<=MAX;i++) {
            //         if(i >= x) {
            //             dp[i] += dp[i-x];
            //             dp[i] %= mod;
            //         }
            //     }
            // }
            // int MAK = (int)1e6 + 10;
            // boolean seive[] = new boolean[MAK];
            // Arrays.fill(seive , true);
            // seive[1] = false;
            // seive[0] = false;
            // for (int i = 1; i < MAK; i++) {
            //     if(seive[i]) {
            //         for (int j = i+i; j < MAK; j+=i) {
            //             seive[j] = false;
            //         }    
            //     }
                
            // }
            // List<Integer>  list = new ArrayList<>();
            // for (int i = 1; i < seive.length; i++) {
            //     if(seive[i]) list.add(i);
            // }
            int test_case = 1;
            while(t-->0) {
                // out.write("Case #"+(test_case++)+": ");
                solve();
            }
            out.close();
        }
        static HashMap<String , Integer> dp;
        static  int size[] , parent[];
        static int comp , max = 1;


        public static void solve()throws IOException {          
            int n = ri() ;
            long arr[] = ral(n);
            boolean ans = callfun(arr ) && callfun(reverse(arr));
            if(ans) {
                System.out.println("YES");
            }
            else System.out.println("NO");
        }
        public static boolean callfun(long arr[]) {
            int n = arr.length;
            for(int i =0;i<n-1;i++) {
                if(arr[i] > 0 && arr[i+1] > 0) {
                    return false;
                }
            }
            List<Long> list = new ArrayList<>();
            for(int i =0;i<n;i++) {
                if(arr[i] == 0) continue;
                if(arr[i] > 0) list.add(arr[i]);
                else {
                    int j = i;
                    long s = 0;
                    while(j < n && arr[j] < 0) {
                        s += arr[j++];
                    }
                    list.add(s);
                    i = j-1;
                }
            }
            for(int i =0;i<list.size();i++) {
                long s = 0 , max = Long.MIN_VALUE;
                for(int j = i;j<=i + 100 && j < list.size();j++) {
                    s += list.get(j);
                    max = Math.max(max , list.get(j));
                    if(max < s) return false;
                }
            }
            return true;

        }
        public static int getAns(int r ,int c,char s[][]) {
            boolean vis[][] = new boolean[s.length][s[0].length];
            vis[r][c] = true;
            Queue<int[]> que = new LinkedList<>();
            for(int i =0;i<4;i++) {
                int nr = r + colx[i] , nc = c + coly[i];
                if(nr < 0 || nc < 0 || nc >= s[0].length || nr >= s.length ) continue;
                que.add(new int[]{nr,nc});
                vis[nr][nc] = true;
            }
            int ans = 1;
            
            
            while(que.size() > 0) {
                int size = que.size();
                
                while(size-- > 0 ) {
                    int top[] = que.poll();
                    // System.out.println(Arrays.toString(top));
                    if(s[top[0]][top[1]] == 'o') {
                      // System.out.println(top[0] +  " " + top[1]);
                      return ans;  
                    } 
                    for(int i =0;i<4;i++) {
                        int nr = top[0] + colx[i] , nc = top[1] + coly[i];
                        if(nr < 0 || nc < 0 || nc >= s[0].length || nr >= s.length) continue;
                        if(vis[nr][nc]) continue;
                        que.add(new int[]{nr,nc});
                        vis[nr][nc] = true;
                    }  
                }
                ans++;
            }
            return Integer.MAX_VALUE;
        }


















        public static int find(int node) {
            if(node == parent[node]) return node;
            return parent[node] = find(parent[node]);
        }
        //https://leetcode.com/kachra123/
        public static void merge(int a ,int b) {
            a = find(a);
            b = find(b);
            if(a == b) return;
            if(size[a] >= size[b]) {
                parent[b] = a;
                size[a] += size[b];
                max = Math.max(max , size[a]);
            }
            else {
                parent[a] = b;
                size[b] += size[a];   
                max = Math.max(max , size[b]);
            }
            comp--;
        }
        public static boolean callfun(int a[] , int b[]) {
            int i =a.length-1 , j =b.length-1;
            while(i >= 0 && j >= 0) {
                if(a[i] >= b[j]) return true;
                else if(a[i] == b[j]) {
                    i--;
                    j--;
                }
                else {
                    return false;
                }
            }
            if(i >= 0) return true;
            else return false;
        }
        public static int getMinIndex(int A[] , int index) {
            int ans = index , min = A[index];
            for(int i =index;i<A.length;i++) {
                if(min > A[i]) {
                    min = A[i];
                    ans = i;
                }
            }
            return ans;
        }
        public static int callfun(long n , long x) {
            String s = x + "";
            if(s.length() > n) {
                return  Integer.MAX_VALUE;
            }
            if(s.length() == n) return 0;
            if(dp.containsKey(s)) return dp.get(s);
            int ans = Integer.MAX_VALUE;
            long temp = x;
            HashSet<Long> set = new HashSet<>();
            while(x > 0) {
                long r = x%10;
                if(r > 1) set.add(r);
                x /= 10;
            }
            for(long r : set ) {
                int curans = callfun(n,temp*r);
                if(curans != Integer.MAX_VALUE) ans = Math.min(ans , curans + 1);
                
            }
            dp.put(s , ans);
            return ans;
        }
        public static  void processPowerOfP(long arr[]) {
            int n = arr.length;
            arr[0] = 1;
            long mod  = (long)1e9 + 7;
            for(int i =1;i<n;i++) {
                arr[i] = arr[i-1]*51;
                arr[i] %= mod;
            }
        }
        public static long hashValue(char s[]) {
            int n = s.length;
            long powerOfP[] = new long[n];
            processPowerOfP(powerOfP);
            long ans =0;
            long mod  = (long)1e9 + 7;
            for(int i =0;i<n;i++) {
                ans += (s[i]-'a'+1)*powerOfP[i];
                ans %= mod;
            }
            return ans;
        }
        public static void dfs(int r,int c,char arr[][]) {
            int n  = arr.length , m = arr[0].length;
            arr[r][c] = '#';
            for(int i =0;i<4;i++) {
                int nr = r + colx[i] , nc = c + coly[i];
                if(nr < 0 || nc < 0 || nc >= m || nr>=n) continue;
                if(arr[nr][nc] == '#') continue;
                dfs(nr,nc,arr);
            }
        }
        
        public static double getSlope(int a , int b,int x,int y) {
            if(a-x == 0) return Double.MAX_VALUE;
            if(b-y == 0) return 0.0;
            return ((double)b-(double)y)/((double)a-(double)x);
        }
        
        public static boolean collinearr(long a[] , long b[] , long c[]) {
            return (b[1]-a[1])*(b[0]-c[0]) == (b[0]-a[0])*(b[1]-c[1]);
        }
        public static boolean isSquare(int sum) {
            int root = (int)Math.sqrt(sum);
            return root*root == sum;
        }
        public void rabinKarp()  {
            int waste = ri();
            char pattern[] = rac() , text[] = rac();
            long power[] = new long[Math.max(pattern.length , text.length)];
            power[0] = 1;
            long p = 31l , mod = (long)1e9 + 9;
            for(int i =1;i<power.length;++i) {
                power[i] = (power[i-1]*p)%mod;
            }
            long hash[] = new long[text.length + 1];
            for(int i =0;i<text.length;i++) {
                hash[i + 1] = (hash[i]  + (text[i]-'a' + 1)*power[i])%mod;
            }
            long hpat = 0;
            for(int i =0;i<pattern.length;i++) {
                hpat = (hpat + (pattern[i]-'a' +1)*power[i])%mod;
            }
            for(int i = 0;i + pattern.length <= text.length;i++) {
                long cur = (hash[i + pattern.length] + mod - hash[i])%mod;
                if(cur == hpat*power[i]%mod) {
                    System.out.println(i);
                }
            }
            System.out.println();
        }

        public static boolean isPalindrome(String s) {
            int i =0 , j = s.length() -1;
            while(i <= j && s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            }
            return i>j;
        }
        // digit dp hint;
        public static long callfun(String num , int N, int last ,int secondLast ,int bound ,long dp[][][][]) {
            if(N == 1) {
                if(last == -1 || secondLast == -1) return 0;
                long answer = 0;
                int max = (bound==1)?(num.charAt(num.length()-N)-'0') : 9;
                for(int i = 0;i<=max;i++) {
                    if(last > secondLast && last > i) {
                        answer++;
                    }
                    if(last < secondLast && last < i) {
                        answer++;
                    }
                }
                return answer;
            }
            if(secondLast == -1 || last == -1 ){
                long answer = 0l;
                int max = (bound==1)?(num.charAt(num.length()-N)-'0') : 9;
                for(int i =0;i<=max;i++)  {
                    int nl , nsl , newbound = bound==0?0:i==max?1:0;
                    if(last == - 1&& secondLast == -1 && i == 0) {
                        nl = -1 ; nsl = -1;
                    }
                    else {
                        nl = i;nsl = last;
                    }

                    long temp = callfun(num , N-1 , nl , nsl ,newbound, dp);
                    answer += temp;
                    if(last != -1 && secondLast != -1 &&((last > secondLast && last > i)||(last < secondLast && last < i))) answer++;
                }
                return answer;    
            }
            if(dp[N][last][secondLast][bound] != -1)  return dp[N][last][secondLast][bound];
            long answer = 0l;
            int max = (bound==1)?(num.charAt(num.length()-N)-'0') : 9;
            for(int i =0;i<=max;i++)  {
                int nl , nsl , newbound = bound==0?0:i==max?1:0;
                if(last == - 1&& secondLast == -1 && i == 0) {
                    nl = -1 ; nsl = -1;
                }
                else {
                    nl = i;nsl = last;
                }
                long temp = callfun(num , N-1 , nl , nsl ,newbound,dp);
                answer += temp;
                if(last != -1 && secondLast != -1 &&((last > secondLast && last > i)||(last < secondLast && last < i))) answer++;
            }
            return dp[N][last][secondLast][bound]  = answer;
        }
        public static Long callfun(int index ,int pair,int arr[],Long dp[][]) {
            long mod = (long)998244353l;
            if(index >= arr.length) return 1l;
            if(dp[index][pair] != null) return dp[index][pair]; 
            Long sum = 0l , ans = 0l;
            if(arr[index]%2 == pair) {
                return dp[index][pair] = callfun(index + 1,pair^1 , arr,dp)%mod + callfun(index + 1 ,pair , arr , dp)%mod;
            }
            else {
                return dp[index][pair] = callfun(index + 1,pair ,  arr,dp)%mod;
            }
            // for(int i =index;i<arr.length;i++) {
            //     sum += arr[i];
            //     if(sum%2 == pair) {
            //         ans = ans  +  callfun(i + 1,pair^1,arr , dp)%mod;
            //         ans%=mod;
            //     }
            // }
            // return dp[index][pair]  = ans;
        }
        public static boolean callfun(int index , int n,int neg , int pos , String s) {
            if(neg < 0 || pos < 0) return false;
            if(index >= n) return true;
            
            if(s.charAt(0) == 'P') {
                if(neg <= 0) return false;
                return callfun(index + 1,n , neg-1 , pos , s.charAt(1)  + "N");
            }
            else  {
                if(pos <= 0) return false;
                return callfun(index + 1 , n , neg , pos-1 , s.charAt(1) + "P");
            }
        }
        
        public static void getPerm(int n , char arr[] , String s ,List<String>list) {
            if(n == 0) {
                list.add(s);
                return;
            }
            for(char ch : arr) {
                getPerm(n-1 , arr , s+ ch,list);
            }
        }
        public static int getLen(int i  ,int j , char s[]) {
            while(i >= 0 && j < s.length && s[i] == s[j]) {
                i--;
                j++;
            }
            i++;
            j--;
            if(i>j) return 0;
            return j-i + 1;
        }
        public static int getMaxCount(String x) {
            char s[] = x.toCharArray();
            int max = 0;
            int n = s.length;
            for(int i =0;i<n;i++) {
                max = Math.max(max,Math.max(getLen(i , i,s) , getLen(i ,i+1,s)));
            }
            return max;
        }
        public static double getDis(int arr[][] , int  x, int y) {
            double ans = 0.0;
            for(int a[] : arr) {
                int x1 = a[0] , y1 = a[1];
                ans += Math.sqrt((x-x1)*(x-x1) + (y-y1)*(y-y1));
            }
            return ans;
        }
        public static boolean valid(String x ) {
            if(x.length() == 0) return true;
            if(x.length() == 1) return false;
            char s[] = x.toCharArray();
            if(x.length()  == 2) {
                if(s[0] == s[1]) {
                    return false;
                }
                return true;
            }
            int r = 0 , b = 0;
            for(char ch : x.toCharArray()) {
                if(ch == 'R') r++;
                else b++;
            }
            return (r >0 && b >0);


        }
        public static long callfun(int day , int k,  int limit,int n) {
            if(k > limit) return 0;
            if(day > n) return 1;
            long ans = callfun(day + 1 , k , limit, n)*k + callfun(day + 1,k+1,limit,n)*(k+1);
            return  ans;
        }

        public static void primeDivisor(HashMap<Long , Long >cnt , long num) {
            for(long i = 2;i*i<=num;i++) {
                while(num%i == 0) {
                    cnt.put(i ,(cnt.getOrDefault(i,0l) + 1));
                    num /= i;
                }
            }
            if(num > 2) {
                cnt.put(num ,(cnt.getOrDefault(num,0l) + 1));
            }
        }
        
        
        public static boolean isSubsequene(char a[],  char b[] ) {
            int i =0 , j = 0;
            while(i < a.length && j  <b.length) {
                if(a[i] == b[j]) {
                    j++;
                }
                i++;
            }
            return  j >= b.length;
        }
        public static long fib(int n ,long M) {
            if (n == 0) {
                return 0;
            } else if (n == 1) {
                return 1;
            } else {
                long[][] mat = {{1, 1}, {1, 0}};
                mat = pow(mat, n-1 , M);
                return mat[0][0];
            }
        }
        public static long[][] pow(long[][] mat, int n ,long M) {
            if (n == 1) return mat;
            else if (n % 2 == 0) return pow(mul(mat, mat , M), n/2 , M);
            else return mul(pow(mul(mat, mat,M), n/2,M), mat , M);
        }
        static long[][] mul(long[][] p, long[][] q,long M) {
            long a = (p[0][0]*q[0][0] + p[0][1]*q[1][0])%M;
            long b = (p[0][0]*q[0][1] + p[0][1]*q[1][1])%M;
            long c = (p[1][0]*q[0][0] + p[1][1]*q[1][0])%M;
            long d = (p[1][0]*q[0][1] + p[1][1]*q[1][1])%M;
            return new long[][] {{a, b}, {c, d}};
        }
        public static long[] kdane(long arr[]) {
            int n = arr.length;
            long dp[] = new long[n];
            dp[0] = arr[0];
            long ans = dp[0];
            for(int i = 1;i<n;i++) {
                dp[i] = Math.max(dp[i-1] + arr[i] , arr[i]);
                ans = Math.max(ans , dp[i]);
            }
            return dp;
        }
        
       
        public static void update(int low , int high , int l , int r, int val , int treeIndex ,int tree[]) {

            if(low > r || high < l || high < low) return;
            if(l <= low && high <= r) {
                System.out.println("At " +low + " and " + high + " ans ttreeIndex  " + treeIndex);
                tree[treeIndex] += val;
                return;
            }
            int mid = low + (high - low)/2;
            update(low , mid , l , r , val , treeIndex*2  + 1, tree);
            update(mid + 1 , high , l , r , val , treeIndex*2 + 2 , tree);
        }
        static int colx[] = {1 ,-1, 0,0 , 1,1,-1,-1};
        static int coly[] = {0 ,0, 1,-1,1,-1,1,-1};
        public static void reverse(char arr[])  {
            int i =0 , j = arr.length-1;
            while(i < j) {
                swap(i , j , arr);
                i++;
                j--;
            }
        }
        public static long[] reverse(long arr[])  {
            long newans[] = arr.clone();
            int i =0 , j = arr.length-1;
            while(i < j) {
                swap(i , j , newans);
                i++;
                j--;
            }
            return newans;
        }
        public static void reverse(int arr[])  {
            int i =0 , j = arr.length-1;
            while(i < j) {
                swap(i , j , arr);
                i++;
                j--;
            }
        }
        
        public static long inverse(long x  , long mod) {
            return pow(x , mod -2 , mod);
        }
        public static int maxArray(int arr[]) {
            int ans = arr[0] ,  n = arr.length;
            for(int i =1;i<n;i++) {
                ans = Math.max(ans , arr[i]);
            }
            return ans;
        }
        public static long maxArray(long arr[]) {
            long ans = arr[0];
            int  n = arr.length;
            for(int i =1;i<n;i++) {
                ans = Math.max(ans , arr[i]);
            }
            return ans;
        }
        public static int minArray(int arr[]) {
            int ans = arr[0] , n = arr.length;
            for(int i =0;i<n;i++ ) {
                ans = Math.min(ans ,arr[i]);
            }
            return ans;
        }
        public static long minArray(long arr[]) {
            long ans = arr[0];
            int n = arr.length;
            for(int i =0;i<n;i++ ) {
                ans = Math.min(ans ,arr[i]);
            }
            return ans;
        }
        public static int sumArray(int arr[]) {
            int ans = 0;
            for(int x : arr) {
                ans  += x;
            }
            return ans;
        }
        public static long sumArray(long arr[]) {
            long ans = 0;
            for(long x : arr) {
                ans  += x;
            }
            return ans;
        }
        public static long rl() {
            return sc.nextLong();
        }
        public static char[] rac() {
            return sc.next().toCharArray();
        }
        public static String rs() {
            return sc.next();
        }
        public static char rc() {
            return sc.next().charAt(0);
        }
        public static  int [] rai(int n) {
            int ans[] = new int[n];
            for(int i =0;i<n;i++) {
                ans[i] = sc.nextInt();
            }
            return ans;
        }
        public static  long [] ral(int n) {
            long ans[] = new long[n];
            for(int i =0;i<n;i++) {
                ans[i] = sc.nextLong();
            }
            return ans;
        }
        public static int ri() {
            return sc.nextInt();
        }

        public static int getValue(int num ) {
            int ans = 0;
            while(num > 0) {
                ans++;
                num = num&(num-1);
            }
            return ans;
        }
        public static boolean isValid(int x ,int y , int n,char arr[][],boolean visited[][][][])  {
            return x>=0 && x<n && y>=0 && y <n && !(arr[x][y] == '#');
        }
        // public static Pair join(Pair a , Pair b) {
        //     Pair res = new Pair(Math.min(a.min , b.min) , Math.max(a.max , b.max) , a.count + b.count);
        //     return res;
        // }
        
        // segment tree query over range
        // public static int query(int node,int l , int r,int a,int b ,Pair tree[] ) {
        //     if(tree[node].max < a || tree[node].min > b) return 0;
        //     if(l  > r) return 0;
        //     if(tree[node].min >= a && tree[node].max <= b) {
        //         return tree[node].count;   
        //     }
        //     int mid = l + (r-l)/2;
        //     int ans = query(node*2 ,l , mid ,a , b , tree) + query(node*2 +1,mid + 1, r , a , b, tree);
        //     return ans;
        // }
        // // segment tree update over range
        // public static void update(int node, int i , int j ,int l , int r,long value, long arr[] ) {
        //     if(l >= i && j >= r) {
        //         arr[node]   += value;
        //         return;
        //     }
        //     if(j < l|| r < i) return;
        //     int mid = l + (r-l)/2;
        //     update(node*2 ,i ,j ,l,mid,value, arr);
        //     update(node*2 +1,i ,j ,mid + 1,r, value  , arr);
        // }

        public static long pow(long a , long b  , long mod) {
            if(b == 1) return a;
            if(b == 0) return 1;
            long ans = pow(a , b/2 , mod)%mod;
            if(b%2 == 0) {
                return (ans*ans)%mod;
            }
            else {
                return ((ans*ans)%mod*a)%mod;
            }
        }
        public static long pow(long a , long b ) {
            if(b == 1) return a;
            if(b == 0) return 1;
            long ans = pow(a , b/2);
            if(b%2 == 0) {
                return (ans*ans);
            }
            else {
                return ((ans*ans)*a);
            }
        }
        
        
        public static boolean isVowel(char ch) {
            if(ch == 'a' || ch == 'e'||ch == 'i' || ch == 'o' || ch == 'u') return true;
            if((ch == 'A' || ch == 'E'||ch == 'I' || ch == 'O' || ch == 'U'))  return true;
            return false;
        }

        
        
        public static int getFactor(int num) {
            if(num==1) return 1;
            int ans = 2;
            int k = num/2;
            for(int i = 2;i<=k;i++) {
                if(num%i==0) ans++;
            }
            return Math.abs(ans);
        }

        public static int[] readarr()throws IOException {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for(int i =0;i<n;i++) {
                arr[i] =  sc.nextInt();
            }
            return arr;
        }
     
        public static boolean isPowerOfTwo (long x) {
            return x!=0 && ((x&(x-1)) == 0);
        }
        public static boolean isPrime(long num) {
            if(num==1) return false;
            if(num<=3) return true;
            if(num%2==0||num%3==0) return false;
            for(long i =5;i*i<=num;i+=6) {
                if(num%i==0 || num%(i+2) == 0) return false;
            }
            return true;
        }
        public static boolean isPrime(int num) {
            // System.out.println("At pr " + num);
            if(num==1) return false;
            if(num<=3) return true;
            if(num%2==0||num%3==0) return false;
            for(int i =5;i*i<=num;i+=6) {
                if(num%i==0 || num%(i+2) == 0) return false;
            }
            return true;
        }
        // public static boolean isPrime(long num) {
        //     if(num==1) return false;
        //     if(num<=3) return true;
        //     if(num%2==0||num%3==0) return false;
        //     for(int i =5;i*i<=num;i+=6) {
        //         if(num%i==0) return false;
        //     }
        //     return true;
        // }
        public static long gcd(long a , long b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }
        public static int gcd(int a , int b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }
        public static int get_gcd(int a , int b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }
        public static long get_gcd(long a , long b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }
        // public static long fac(long num)  {
        //     long ans = 1;
        //     int mod = (int)1e9+7;
        //     for(long i = 2;i<=num;i++) {
        //         ans  =  (ans*i)%mod;
        //     }
        //     return ans;
        // }
    }