class A:
    def ping(self):
        print("A")
        # end of chain

class B(A):
    def ping(self):
        print("B ->", end=" ")
        super().ping()

class C(A):
    def ping(self):
        print("C ->", end=" ")
        super().ping()

class D(B, C):
    def ping(self):
        print("D ->", end=" ")
        super().ping()

print(D.mro())
D().ping()