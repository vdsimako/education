class Base:
    def save(self):
        print("Base.save")

class AnotherBase():
    def save(self):
        print("AnotherBase.save")

class Audit(Base, AnotherBase):
    def save(self):
        print("Audit.before")
        super().save()
        print("Audit.after")

class AnotherAudit(AnotherBase, Base):

    def save(self):
        print("AnotherAudit.before")
        super().save()
        print("AnotherAudit.after")


b = Base()
b.save()

Audit().save()
AnotherAudit().save()