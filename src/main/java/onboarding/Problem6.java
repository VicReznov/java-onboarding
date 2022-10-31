package onboarding;

import java.util.*;

public class Problem6 {
    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = new ArrayList<>();

        Map<String, String> listMap = new HashMap<>();
        String[] nickList = new String[forms.size()];
        // 리스트에 있는 닉네임과 이메일을 맵에 넣음
        Iterator<List<String>> listItr = forms.iterator();
        for(int i=0; i<forms.size(); i++){
            List<String> nowList = listItr.next();
            Iterator<String > nowListItr = nowList.iterator();
            String email = nowListItr.next();
            String nickname = nowListItr.next();

            nickList[i] = nickname;
            listMap.put(nickname, email);
        }

        // 닉네임 중 같은 글자가 연속적으로 포함 되는 경우 찾기
        // 중복 확인할 기준 문자열 구하고 닉네임 set에 넣기
        Set<String> collectNickname = selectDuplicatedNickname(nickList);

        // 보낼 이메일 구하고 오름차순 정렬
        String[] makeEmailList = new String[collectNickname.size()];
        Iterator<String> collectNicknameItr = collectNickname.iterator();
        for(int i=0; i<makeEmailList.length; i++){
            String nickName = collectNicknameItr.next();
            String email = listMap.get(nickName);
            makeEmailList[i] = email;
        }

        Arrays.sort(makeEmailList);

        for(int i=0; i<makeEmailList.length; i++){
            answer.add(makeEmailList[i]);
        }

        return answer;
    }

    /**
     * 닉네임에서 중복을 찾아낼 기준 문자열 구하는 메소드
     * @param name 기준 문자열 구할 때 쓸 닉네임
     * @param criteriaLength 구할 기준 문자열의 길이
     * @return 구한 기준 문자열을 넣어둔 배열을 반환
     */
    static String[] makeCriteria(String name, int criteriaLength){
        String[] criteriaList = new String[name.length() - criteriaLength + 1];

        for(int i=0; i<=name.length()-criteriaLength; i++){
            criteriaList[i] = name.substring(i, i+criteriaLength);
        }

        return criteriaList;
    }

    /**
     * 중복이 있는 닉네임 찾는 메소드
     * @param nickList 입력받은 닉네임 리스트
     * @param nowNameIdx 기준 문자열을 만들 때 사용한 닉네임의 인덱스
     * @param criteriaList 구한 기준 문자열 리스트
     * @return 중복이 있는 닉네임을 리스트로 반환
     */
    static List<String> findDuplicatedNickname(String[] nickList, int nowNameIdx, String[] criteriaList){
        List<String> dup = new ArrayList<>();
        for(int i=0; i<criteriaList.length; i++){
            for(int j=0; j<nickList.length; j++){
                if(j == nowNameIdx) continue;

                if(nickList[j].contains(criteriaList[i])){
                    dup.add(nickList[j]);
                }
            }
        }

        return dup;
    }

    /**
     * 중복되는 문자열이 있는 모든 닉네임을 set에 넣는 메소드
     * @param nickList 모든 닉네임 리스트
     * @return 구한 set
     */
    static Set<String> selectDuplicatedNickname(String[] nickList){
        Set<String> collectNickname = new HashSet<>();

        for(int i=0; i<nickList.length; i++){
            String name = nickList[i];
            // 중복 확인할 때 쓸 문자열 구하기
            String[] criteriaList = makeCriteria(name, 2);

            // 닉네임들 중에 기준 문자열과 중복인 닉네임이 있는지 찾음
            List<String> duplicatedNickname = findDuplicatedNickname(nickList, i, criteriaList);

            Iterator<String> dupNicknameItr = duplicatedNickname.iterator();
            for(int s=0; s<duplicatedNickname.size(); s++){
                collectNickname.add(dupNicknameItr.next());
            }
        }

        return collectNickname;
    }

}
