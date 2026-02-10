from collections import defaultdict

##############################################################################################################################
# 3.1 defaultdict
groups = defaultdict(list)
groups["a"].append(1)
groups["a"].append(2)

print(groups["a"])  # [1, 2]

t = [(1, 2), (2, 3), (3, 4)]
g = {}
for k, v in t:
    g[k] = v
print(g)
g = defaultdict(list)
for k, v in t:
    g[k].append(v)
print(g)
##############################################################################################################################
# 3.2 Counter
from collections import Counter

cnt = Counter("aabbbc")
print(cnt)  # Counter({'b': 3, 'a': 2, 'c': 1})
print(cnt.most_common(2))  # [('b', 3), ('a', 2)]
print(cnt.most_common(1))  # [('b', 3)]


def top_k_words(text, k):
    cnt = Counter(text.split())
    return cnt.most_common(k)


print(top_k_words("a b c a b", 2))

##############################################################################################################################
# 3.3 deque
from collections import deque

q = deque(maxlen=3)
q.append(1)
q.append(2)
q.append(3)
q.append(4)

print(list(q))  # [2, 3, 4]

q = deque()
q.append(1)
q.append(2)
q.append(3)
q.append(4)
print(q.popleft())
print(list(q))

