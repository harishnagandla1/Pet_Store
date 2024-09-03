public class JobUser {
private String name;
private String job;

private JobUser(JobUserBuilder builder) {
this.name = builder.name;
this.job = builder.job;
}

public static class JobUserBuilder {
private String name;
private String job;

public JobUserBuilder setName(String name) {
this.name = name;
return this;
}

public JobUserBuilder setJob(String job) {
this.job = job;
return this;
}

public JobUser build() {
return new JobUser(this);
}
}
}