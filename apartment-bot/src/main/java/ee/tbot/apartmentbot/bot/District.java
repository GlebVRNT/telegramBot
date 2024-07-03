package ee.tbot.apartmentbot.bot;

public enum District {
  MUSTAMAE("/mustamae", "2413"),
  LASNAMAE("/lasnamae", "1897"),
  KOPLI("/kopli", "3166"),
  HAABERSTI("/haabersti", "540"),
  KRISTIINE("/kristiine", "1535"),
  NOMME("/nomme", "2670"),
  KESKLINN("/kesklinn", "1240");

  private final String command;
  private final String code;

  District(String command, String code) {
    this.command = command;
    this.code = code;
  }

  public String getCommand() {
    return command;
  }

  public String getCode() {
    return code;
  }
}
