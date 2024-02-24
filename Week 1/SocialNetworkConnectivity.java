class SocialNetworkConnectivity {
  UnionFind uf;
  int n;

  public SocialNetworkConnectivity(int n) {
    this.n = n;
    uf = new UnionFind(n);
  }

  public String findEarliestTime(LogEntry[] logs) {
    for (LogEntry log : logs) {
      int member1 = log.member1;
      int member2 = log.member2;
      String timestamp = log.timestamp;

      if (!uf.connected(member1, member2)) {
        uf.union(member1, member2);
        if (uf.getComponents() == 1) {
          return timestamp;
        }
      }
    }
    return "Not all members are connected";
  }
}
