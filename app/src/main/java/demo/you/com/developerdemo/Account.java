package demo.you.com.developerdemo;


public class Account {
    private int accountImg;
    private String accountName;
    private int accountShareCount;
    private int readMeCount;

    private int infoImg;
    private String infoName;
    private String spcText;
    private String simpleText;

    private boolean isTeam;
    private int memberCount;
    private String signature;
    private boolean isSupportMass;

    private String essayTitle;
    private int likeCount;
    private int commentCount;
    private boolean isLike;

    public Account(int infoImg, String infoName, String spcText, String simpleText) {
        this.infoImg = infoImg;
        this.infoName = infoName;
        this.spcText = spcText;
        this.simpleText = simpleText;
    }

    public Account(int accountImg, String accountName,boolean isSupportMass, String signature, int memberCount, boolean isTeam, int readMeCount, int accountShareCount) {
        this.accountImg = accountImg;
        this.accountName = accountName;
        this.isSupportMass=isSupportMass;
        this.signature = signature;
        this.memberCount = memberCount;
        this.isTeam = isTeam;
        this.readMeCount = readMeCount;
        this.accountShareCount = accountShareCount;
    }

    public Account(int accountImg, String accountName, int accountShareCount, int readMeCount) {
        this.accountImg = accountImg;
        this.accountName = accountName;
        this.accountShareCount = accountShareCount;
        this.readMeCount = readMeCount;
    }

    public Account(int accountImg, String accountName, int commentCount, int likeCount, String essayTitle, boolean isLike) {
        this.accountImg = accountImg;
        this.accountName = accountName;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.essayTitle = essayTitle;
        this.isLike=isLike;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getEssayTitle() {
        return essayTitle;
    }

    public void setEssayTitle(String essayTitle) {
        this.essayTitle = essayTitle;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isSupportMass() {
        return isSupportMass;
    }

    public void setSupportMass(boolean supportMass) {
        isSupportMass = supportMass;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isTeam() {
        return isTeam;
    }

    public void setTeam(boolean team) {
        isTeam = team;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(int accountImg) {
        this.accountImg = accountImg;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountShareCount() {
        return accountShareCount;
    }

    public void setAccountShareCount(int accountShareCount) {
        this.accountShareCount = accountShareCount;
    }

    public int getReadMeCount() {
        return readMeCount;
    }

    public void setReadMeCount(int readMeCount) {
        this.readMeCount = readMeCount;
    }

    public int getInfoImg() {
        return infoImg;
    }

    public void setInfoImg(int infoImg) {
        this.infoImg = infoImg;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getSpcText() {
        return spcText;
    }

    public void setSpcText(String spcText) {
        this.spcText = spcText;
    }

    public String getSimpleText() {
        return simpleText;
    }

    public void setSimpleText(String simpleText) {
        this.simpleText = simpleText;
    }
}
