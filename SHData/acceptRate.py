#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Aug  2 14:30:04 2018

@author: carsonluuu
"""

import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

def dfs(dep, ans, List, setList):
    import copy
    if (dep == len(setList)):
        ans.append(copy.deepcopy(List))
        return
    for i in setList[dep]:
        List.append(i)
        dfs(dep + 1, ans, List, setList)
        List.pop()
    return 
def getSet(setList):
    # Write your code here  
    ans = []
    dfs(0, ans, [], setList)
    return ans

df = pd.read_json("driversInit.json")
did = df['did'].tolist()
belong = df['belong'].tolist()

time = [0, 1, 2]
dist = [0, 1, 2, 3]
ETA = [0, 1]
loc = [0, 1]

feature_list = [time, dist, ETA, loc]   
res = getSet(feature_list)

features = []

for elem in res:
    features.append(''.join(str(e) for e in elem))

nums = 0.5 * np.random.rand(len(res), len(did))

AR = pd.DataFrame(nums, columns=did, index=features)

for index, row in AR.iterrows():
    i = 0
    for d in did:
        if index[-1] == '0' and belong[i] == 'outter':
            row[d] = 0
        elif index[-1] == '1' and belong[i] == 'outter':
            row[d] = 1.2 * row[d]
        i = i + 1
