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

    // 题目说明
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


    // 题目标签信息
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

    @Deprecated
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


    private static final String questionEditorDataStr = "{\n" +
            "    \"query\": \"\\n    query questionEditorData($titleSlug: String!) {\\n  question(titleSlug: $titleSlug) {\\n    questionId\\n    questionFrontendId\\n    codeSnippets {\\n      lang\\n      langSlug\\n      code\\n    }\\n    envInfo\\n    enableRunCode\\n    hasFrontendPreview\\n    frontendPreviews\\n  }\\n}\\n    \",\n" +
            "    \"variables\": {\n" +
            "        \"titleSlug\": \"%s\"\n" +
            "    },\n" +
            "    \"operationName\": \"questionEditorData\"\n" +
            "}";


    /**
     * 新版本接口
     * weekly-contest-398
     * special-array-i
     */
    private static final String newContestQuestionJSONStr =
            "{\n" +
                    "    \"query\": \"\\n    query contestQuestion($contestSlug: String, $questionSlug: String) {\\n  contestDetail(contestSlug: $contestSlug) {\\n    startTime\\n    duration\\n    titleSlug\\n    failCount\\n    enableContestDynamicLayout\\n    isDynamicLayout\\n    hasCompletedContest\\n    isVirtualContest\\n  }\\n  contestQuestion(contestSlug: $contestSlug, questionSlug: $questionSlug) {\\n    totalAc\\n    totalSubmission\\n    totalTriedUser\\n    totalAcUser\\n    languageList {\\n      id\\n      name\\n      verboseName\\n    }\\n    submittableLanguageList {\\n      id\\n      name\\n      verboseName\\n    }\\n    question {\\n      status\\n      questionId\\n      questionFrontendId\\n      enableRunCode\\n      enableSubmit\\n      enableTestMode\\n      metaData\\n      title\\n      titleSlug\\n      difficulty\\n      categoryTitle\\n      codeSnippets {\\n        code\\n        lang\\n        langSlug\\n      }\\n      exampleTestcaseList\\n      canSeeQuestion\\n      envInfo\\n      content\\n      translatedTitle\\n      translatedContent\\n    }\\n  }\\n}\\n    \",\n" +
                    "    \"variables\": {\n" +
                    "        \"contestSlug\": \"%s\",\n" +
                    "        \"questionSlug\": \"%s\"\n" +
                    "    },\n" +
                    "    \"operationName\": \"contestQuestion\"\n" +
                    "}";
    public static String newContestQuestion(String contestSlug,String questionSlug) {
        return String.format(newContestQuestionJSONStr, contestSlug,questionSlug);
    }


    /**
     * 查询编辑器状态
     *
     * @param titleSlug
     * @return
     */
    public static String questionEditorData(String titleSlug) {
        return String.format(questionEditorDataStr, titleSlug);
    }


    private static final String userStatusStr = "{\n" +
            "    \"variables\": {},\n" +
            "    \"query\": \"\\n        query globalData {\\n          userStatus {\\n            isSignedIn\\n            isPremium\\n            username\\n            realName\\n            avatar\\n            userSlug\\n            isAdmin\\n            checkedInToday\\n            useTranslation\\n            premiumExpiredAt\\n            isTranslator\\n            isSuperuser\\n            isPhoneVerified\\n            isVerified\\n          }\\n          jobsMyCompany {\\n            nameSlug\\n          }\\n          commonNojPermissionTypes\\n        }\\n      \"\n" +
            "}";

    public static String userStatus() {
        return userStatusStr;
    }

    public static final String queryTokenStr = "{\n" +
            "    \"operationName\": \"ipInfo\",\n" +
            "    \"variables\": {},\n" +
            "    \"query\": \"query ipInfo {\\n  ipInfo {\\n    country\\n    countryCode\\n    __typename\\n  }\\n}\\n\"\n" +
            "}";

    public static String queryToken() {
        return queryTokenStr;
    }


    private static final String submitCodeString = "{\n" +
            "    \"lang\": \"java\",\n" +
            "    \"question_id\": \"%s\",\n" +
            "    \"typed_code\": \" %s\"\n" +
            "}";
    public static String submitCode(String questionID,String code) {
        return String.format(submitCodeString,questionID,code);
    }


    public static final String queryQuestionInfo = "{\n" +
            "    \"query\": \"\\n    query questionDetail($titleSlug: String!) {\\n  languageList {\\n    id\\n    name\\n    verboseName\\n  }\\n  statusList {\\n    id\\n    name: translatedName\\n  }\\n  question(titleSlug: $titleSlug) {\\n    title\\n    titleSlug\\n    questionId\\n    questionFrontendId\\n    questionTitle\\n    translatedTitle\\n    content\\n    translatedContent\\n    categoryTitle\\n    difficulty\\n    stats\\n    style\\n    contributors {\\n      username\\n      profileUrl\\n      avatarUrl\\n    }\\n    book {\\n      id\\n      bookName\\n      pressName\\n      source\\n      shortDescription\\n      fullDescription\\n      bookImgUrl\\n      pressImgUrl\\n      productUrl\\n    }\\n    companyTagStatsV2\\n    topicTags {\\n      name\\n      slug\\n      translatedName\\n    }\\n    similarQuestions\\n    mysqlSchemas\\n    dataSchemas\\n    frontendPreviews\\n    likes\\n    dislikes\\n    isPaidOnly\\n    status\\n    boundTopicId\\n    enableTestMode\\n    metaData\\n    enableRunCode\\n    enableSubmit\\n    envInfo\\n    isLiked\\n    nextChallengePairs\\n    libraryUrl\\n    hints\\n    codeSnippets {\\n      code\\n      lang\\n      langSlug\\n    }\\n    jsonExampleTestcases\\n    exampleTestcases\\n    sampleTestCase\\n    hasFrontendPreview\\n    editorType\\n    featuredContests {\\n      titleSlug\\n      titleCn\\n      title\\n    }\\n  }\\n}\\n    \",\n" +
            "    \"variables\": {\n" +
            "        \"titleSlug\": \"%s\"\n" +
            "    },\n" +
            "    \"operationName\": \"questionDetail\"\n" +
            "}";

    public static String getQueryQuestionInfo(String titleSlug) {
        return String.format(queryQuestionInfo,titleSlug);
    }

    private static final String submitDetailString = "{\"query\":\"\\n    query submissionDetails($submissionId: ID!) {\\n  submissionDetail(submissionId: $submissionId) {\\n    code\\n    timestamp\\n    statusDisplay\\n    isMine\\n    runtimeDisplay: runtime\\n    memoryDisplay: memory\\n    memory: rawMemory\\n    lang\\n    langVerboseName\\n    question {\\n      questionId\\n      titleSlug\\n      hasFrontendPreview\\n    }\\n    user {\\n      realName\\n      userAvatar\\n      userSlug\\n    }\\n    runtimePercentile\\n    memoryPercentile\\n    submissionComment {\\n      flagType\\n    }\\n    passedTestCaseCnt\\n    totalTestCaseCnt\\n    fullCodeOutput\\n    testDescriptions\\n    testInfo\\n    testBodies\\n    stdOutput\\n    ... on GeneralSubmissionNode {\\n      outputDetail {\\n        codeOutput\\n        expectedOutput\\n        input\\n        compileError\\n        runtimeError\\n        lastTestcase\\n      }\\n    }\\n    ... on ContestSubmissionNode {\\n      outputDetail {\\n        codeOutput\\n        expectedOutput\\n        input\\n        compileError\\n        runtimeError\\n        lastTestcase\\n      }\\n    }\\n  }\\n}\\n    \",\"variables\":{\"submissionId\":\"%s\"},\"operationName\":\"submissionDetails\"}";
    public static String querySubmitStatus(String submissionId) {
        return String.format(submitDetailString,submissionId);
    }



    public static void main(String[] args) {
        System.out.println(userStatusStr);
    }



}
