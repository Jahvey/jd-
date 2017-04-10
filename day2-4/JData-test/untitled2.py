# -*- coding: utf-8 -*-
"""
Created on Fri Apr  7 19:59:03 2017

@author: Administrator
"""


import pandas as pd
import numpy as np


PROTENTIAL_USER_RECORD = "data2/protential_user_record.csv"
RESULTS = "data2/results.csv"



def buy_user_in_batch_data1(fname, chunk_size=100000):
    reader = pd.read_csv(fname, header=0, iterator=True)
    chunks = []
    loop = True
    while loop:
        try:
            chunk = reader.get_chunk(chunk_size)[
                ["user_id", "sku_id", "type"]]
            chunks.append(chunk)
        except StopIteration:
            loop = False
            print("Iteration is stopped")

    df_ac = pd.concat(chunks, ignore_index=True)

    # find buy record
    df_ac = df_ac[df_ac['type'] == 6][["user_id", "sku_id"]]

    return df_ac


def find_buy_user1():
    df_ac = []
    df_ac.append(buy_user_in_batch_data1(fname=PROTENTIAL_USER_RECORD))


    df_ac = pd.concat(df_ac, ignore_index=True)
    df_ac = df_ac.drop_duplicates()

    df_ac.to_csv(RESULTS, index=False)



find_buy_user1()
