package code_generation.crwal.leetcode;

import code_generation.crwal.TestCaseUtil;
import code_generation.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Question {
    public String category_slug;
    public String credit;
    public String id;
    public String question_id;
    public String title;
    public String title_slug;
    private String url;
    public String api_url;
    public String contestNo;
    private boolean isContest = true;

    public Question buildIsContest(boolean isContest) {
        this.isContest = isContest;
        return this;
    }


    public String getUrl() {
        if (isContest) {
            checkContestUrl(api_url, title_slug);
        }

        return url;
    }


    public Question buildApiUrl(String api_url) {
        if (isContest) {
            checkContestUrl(api_url, title_slug);
            this.url = api_url.replace("api/info/", "") + "problems/" + title_slug;
        }
        this.api_url = api_url;
        return this;
    }

    public Question buildContestNo(String contestNo) {
        this.contestNo = contestNo;
        return this;
    }


    public Question buildCategorySlug(String category_slug) {
        this.category_slug = category_slug;
        return this;
    }

    public Question buildCredit(String credit) {
        this.credit = credit;
        return this;
    }

    public Question buildTitle(String title) {
        this.title = title;
        return this;
    }

    public Question buildTitleSlug(String title_slug) {
        this.title_slug = title_slug;
        return this;
    }

    public Question builderId(String id) {
        this.id = id;
        return this;
    }

    public Question buildQuestionId(String question_id) {
        this.question_id = question_id;
        return this;
    }

    public String getCategory_slug() {
        return category_slug;
    }

    public String getCredit() {
        return credit;
    }

    public String getId() {
        return id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle_slug() {
        return title_slug;
    }

    public String getApi_url() {
        return api_url;
    }

    public String getContestNo() {
        return contestNo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Question.class.getSimpleName() + "[", "]")
                .add("category_slug='" + category_slug + "'")
                .add("credit='" + credit + "'")
                .add("id='" + id + "'")
                .add("question_id='" + question_id + "'")
                .add("title='" + title + "'")
                .add("title_slug='" + title_slug + "'")
                .add("url='" + url + "'")
                .add("api_url='" + api_url + "'")
                .add("contentNo='" + contestNo + "'")
                .toString();
    }

    public static void checkContestUrl(String api_url, String title_slug) {
        if (StringUtils.isEmpty(title_slug)) {
            throw new RuntimeException("place config title_slug");
        }
        if (api_url == null
                || !api_url.startsWith("https://leetcode.cn/contest/api/info")
                || (!api_url.contains("biweekly-contest") && !api_url.contains("weekly-contest"))
        ) {
            throw new RuntimeException("api url prefix is https://leetcode.cn/contest/api/info , and suffix is biweekly-contest-{number} or weekly-contest-{number} ");
        }
    }


    public static List<Question> getContestQuestion(String jsonStr, String api_url) {
        String p = parse(jsonStr, "questions", '[', ']');
        List<String> parses = parse(p, '{', '}');
        List<Question> qs = new ArrayList<>();
        String NoId = StringUtils.parseId(api_url);
        for (String s : parses) {
            Question question = new Question();
            String id = StringUtils.jsonStrGetValueByKey(s, "id");
            String question_id = StringUtils.jsonStrGetValueByKey(s, "question_id");
            String title = StringUtils.jsonStrGetValueByKey(s, "title");
            String credit = StringUtils.jsonStrGetValueByKey(s, "credit");
            String title_slug = StringUtils.jsonStrGetValueByKey(s, "title_slug");
            String category_slug = StringUtils.jsonStrGetValueByKey(s, "category_slug");
            question.buildTitleSlug(title_slug);
            question.buildTitle(title);
            question.buildCredit(credit);
            question.buildCategorySlug(category_slug);
            question.builderId(id);
            question.buildQuestionId(question_id);
            question.buildApiUrl(api_url);
            question.buildContestNo(NoId);
            qs.add(question);
        }
        return qs;
    }

    public static String parse(String input, String name, char start, char end) {

        char[] charArray = input.toCharArray();
        StringBuilder sb = null;
        int pos = StringUtils.kmpSearch(input, name);
        if (pos == -1) {
            throw new RuntimeException("Not find " + name);
        }
        int deep = 0;
        for (int i = pos; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == ':' && deep == 0) {
                sb = new StringBuilder();
                continue;
            } else if (c == start) {
                deep++;
            } else if (c == end) {
                deep--;
                if (deep == 0) {
                    break;
                }
            }
            if (sb != null) {
                sb.append(c);
            }
        }
        return sb == null ? "" : sb.toString();
    }


    public static List<String> parse(String input, char start, char end) {
        List<String> ans = new ArrayList<>();
        char[] charArray = input.toCharArray();
        StringBuilder sb = null;
        int deep = 0;
        for (char c : charArray) {
            if (c == start) {
                deep++;
                sb = new StringBuilder();
                sb.append(c);
            } else if (c == end) {
                deep--;
                if (deep == 0) {
                    if (sb != null) {
                        sb.append(c);
                        ans.add(sb.toString());
                        sb = null;
                    }
                }
            } else {
                if (sb != null) {
                    sb.append(c);
                }
            }

        }
        return ans;
    }


}
