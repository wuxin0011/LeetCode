package code_generation.crwal.leetcode;

/**
 * @author: wuxin0011
 * @Description:
 */
public class LCJsonTemplate {




    private static final String questionOfTodayStr = "{\n" +
            "    \"query\": \"\\n    query questionOfToday {\\n  todayRecord {\\n    date\\n    userStatus\\n    question {\\n      questionId\\n      frontendQuestionId: questionFrontendId\\n      difficulty\\n      title\\n      titleCn: translatedTitle\\n      titleSlug\\n      paidOnly: isPaidOnly\\n      freqBar\\n      isFavor\\n      acRate\\n      status\\n      solutionNum\\n      hasVideoSolution\\n      topicTags {\\n        name\\n        nameTranslated: translatedName\\n        id\\n      }\\n      extra {\\n        topCompanyTags {\\n          imgUrl\\n          slug\\n          numSubscribed\\n        }\\n      }\\n    }\\n    lastSubmission {\\n      id\\n    }\\n  }\\n}\\n    \",\n" +
            "    \"variables\": {},\n" +
            "    \"operationName\": \"questionOfToday\"\n" +
            "}";

    public static String questionOfToday() {
        return questionOfTodayStr;
    }


    private static final String questionTitleStr = "{\n" +
            "    \"query\": \"\\n    query questionTitle($titleSlug: String!) {\\n  question(titleSlug: $titleSlug) {\\n    questionId\\n    questionFrontendId\\n    title\\n    titleSlug\\n    isPaidOnly\\n    difficulty\\n    likes\\n    dislikes\\n    categoryTitle\\n  }\\n}\\n    \",\n" +
            "    \"variables\": {\n" +
            "        \"titleSlug\": \"%s\"\n" +
            "    },\n" +
            "    \"operationName\": \"questionTitle\"\n" +
            "}";

    public static String questionTitle(String titleSlug) {
        return String.format(questionTitleStr, titleSlug);
    }

    private static final String questionTranslationStr = "{\n" +
            "    \"query\": \"\\n    query questionTranslations($titleSlug: String!) {\\n  question(titleSlug: $titleSlug) {\\n    translatedTitle\\n    translatedContent\\n  }\\n}\\n    \",\n" +
            "    \"variables\": {\n" +
            "        \"titleSlug\": \"%s\"\n" +
            "    },\n" +
            "    \"operationName\": \"questionTranslations\"\n" +
            "}";

    public static String questionTranslations(String titleSlug) {
        return String.format(questionTranslationStr, titleSlug);
    }


    private static final String singleQuestionTopicTagsStr = "{\n" +
            "    \"query\": \"\\n    query singleQuestionTopicTags($titleSlug: String!) {\\n  question(titleSlug: $titleSlug) {\\n    topicTags {\\n      name\\n      slug\\n      translatedName\\n    }\\n  }\\n}\\n    \",\n" +
            "    \"variables\": {\n" +
            "        \"titleSlug\": \"%s\"\n" +
            "    },\n" +
            "    \"operationName\": \"singleQuestionTopicTags\"\n" +
            "}";

    public static String singleQuestionTopicTags(String suffixUrl) {
        return String.format(singleQuestionTopicTagsStr, suffixUrl);
    }


    private static final String questionTestCase = "{\n" +
            "    \"query\": \"\\n    query consolePanelConfig($titleSlug: String!) {\\n  question(titleSlug: $titleSlug) {\\n    questionId\\n    questionFrontendId\\n    questionTitle\\n    enableRunCode\\n    enableSubmit\\n    enableTestMode\\n    jsonExampleTestcases\\n    exampleTestcases\\n    metaData\\n    sampleTestCase\\n  }\\n}\\n    \",\n" +
            "    \"variables\": {\n" +
            "        \"titleSlug\": \"%s\"\n" +
            "    },\n" +
            "    \"operationName\": \"consolePanelConfig\"\n" +
            "}";

    public static String createQuestionTestCase(String suffixUrl) {
        return String.format(questionTestCase, suffixUrl);
    }

    private static final String totalInfo = "{\n" +
            "\"operationName\": \"globalData\",\n" +
            "\"query\":         \"query globalData {\n  feature {\n    questionTranslation\n    subscription\n    signUp\n    discuss\n    mockInterview\n    contest\n    store\n    book\n    chinaProblemDiscuss\n    socialProviders\n    studentFooter\n    cnJobs\n    __typename\n  }\n  userStatus {\n    isSignedIn\n    isAdmin\n    isStaff\n    isSuperuser\n    isTranslator\n    isPremium\n    isVerified\n    isPhoneVerified\n    isWechatVerified\n    checkedInToday\n    username\n    realName\n    userSlug\n    groups\n    jobsCompany {\n      nameSlug\n      logo\n      description\n      name\n      legalName\n      isVerified\n      permissions {\n        canInviteUsers\n        canInviteAllSite\n        leftInviteTimes\n        maxVisibleExploredUser\n        __typename\n      }\n      __typename\n    }\n    avatar\n    optedIn\n    requestRegion\n    region\n    activeSessionId\n    permissions\n    notificationStatus {\n      lastModified\n      numUnread\n      __typename\n    }\n    completedFeatureGuides\n    useTranslation\n    __typename\n  }\n  siteRegion\n  chinaHost\n  websocketUrl\n}\n\",\n" +
            "\t\t}";

    public static String getTotalInfo() {
        return totalInfo;
    }

    private static final String userInfo = "{\n" +
            "    \"operationName\": \"userStatusGlobal\",\n" +
            "    \"variables\": {},\n" +
            "    \"query\": \"query userStatusGlobal {\\n  userStatus {\\n    isSignedIn\\n    isAdmin\\n    isStaff\\n    isSuperuser\\n    isTranslator\\n    isVerified\\n    isPhoneVerified\\n    isWechatVerified\\n    checkedInToday\\n    username\\n    realName\\n    userSlug\\n    groups\\n    avatar\\n    optedIn\\n    requestRegion\\n    region\\n    socketToken\\n    activeSessionId\\n    permissions\\n    completedFeatureGuides\\n    useTranslation\\n    accountStatus {\\n      isFrozen\\n      inactiveAfter\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"\n" +
            "}";

    public static String getUserInfo() {
        return totalInfo;
    }




    private static final String commentInfoStr = "{\n" +
            "    \"query\": \"\\n    query questionDiscussComments($topicId: Int!, $orderBy: CommentOrderBy, $skip: Int!, $numPerPage: Int = 10) {\\n  commonTopicComments(\\n    topicId: $topicId\\n    orderBy: $orderBy\\n    skip: $skip\\n    first: $numPerPage\\n  ) {\\n    edges {\\n      node {\\n        ...commentFields\\n      }\\n    }\\n    totalNum\\n  }\\n}\\n    \\n    fragment commentFields on CommentRelayNode {\\n  id\\n  ipRegion\\n  numChildren\\n  isEdited\\n  post {\\n    id\\n    content\\n    voteUpCount\\n    creationDate\\n    updationDate\\n    status\\n    voteStatus\\n    isOwnPost\\n    author {\\n      username\\n      isDiscussAdmin\\n      isDiscussStaff\\n      profile {\\n        userSlug\\n        userAvatar\\n        realName\\n      }\\n    }\\n    mentionedUsers {\\n      key\\n      username\\n      userSlug\\n      nickName\\n    }\\n  }\\n}\\n    \",\n" +
            "    \"variables\": {\n" +
            "        \"topicId\": 2662371,\n" +
            "        \"skip\": 0,\n" +
            "        \"numPerPage\": 10,\n" +
            "        \"orderBy\": \"HOT\"\n" +
            "    },\n" +
            "    \"operationName\": \"questionDiscussComments\"\n" +
            "}";

    public static String questionDiscussComments() {
        return commentInfoStr;
    }


}
