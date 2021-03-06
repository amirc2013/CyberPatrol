__author__ = 'Oded  Hupert'

from sklearn import datasets
from sklearn import feature_extraction
from sklearn import preprocessing
from sklearn import svm
from sklearn import feature_selection
from sklearn.neighbors import KNeighborsClassifier
import os
import codecs
import dill as pickle
from sklearn import grid_search
from sklearn import cross_validation
from sklearn.cross_validation import train_test_split
from sklearn import metrics

print("Importing data  from folder...")
# here we create a Bunch
# object ['target_names', 'data', 'target', 'DESCR', 'filenames']
raw_bunch = datasets.load_files('./data', description=None, categories=None, load_content=True,
                                shuffle=True, encoding='utf-8', decode_error='replace')
print("Done!")
print("Processing text to data...")
print("     extracting features...")
vectorizer = feature_extraction.text.CountVectorizer(input=u'content', encoding=u'utf-8', decode_error=u'strict',
                                                     strip_accents=None, lowercase=True,
                                                     preprocessor=None, tokenizer=None,
                                                     # trying with enabled parameters
                                                     stop_words=None,  # we count stop words
                                                     token_pattern=r'(?u)\b\w\w+\b',
                                                     ngram_range=(1, 3),  # 1grams to 3grams
                                                     analyzer=u'word', max_df=1.0, min_df=1, max_features=None,
                                                     vocabulary=None, binary=False,
                                                     dtype='f')
raw_documents = raw_bunch['data']
document_feature_matrix = vectorizer.fit_transform(raw_documents)
feature_names = vectorizer.get_feature_names()
print("     Done!")
# print("     scaling ...")
# scaler = preprocessing.StandardScaler(copy=True, with_mean=False, with_std=True).fit(document_feature_matrix)
# We are using sparse matrix so we have to define with_mean=False
# Scaler can be used later for new objects
# document_term_matrix_scaled = scaler.transform(document_feature_matrix)
# print("     Done!")
print("Done!")

print("Building a classifier...")
# print("     selecting features ...")
# var = feature_selection.VarianceThreshold(threshold=(0.2 * (1 - 0.2)))
# document_feature_matrix = var.fit_transform(document_feature_matrix)
# k_best = feature_selection.SelectKBest(k=10)
# document_feature_matrix = k_best.fit_transform(document_feature_matrix, raw_bunch['target'])
print("     training...")

print("---------------------------------------")


# STEP 2 - Tries
X_train, X_test, y_train, y_test = train_test_split(document_feature_matrix,
                                                    raw_bunch['target'], test_size=0.1, random_state=42)

clf = svm.SVC(C=0.01, kernel="linear")
# clf = KNeighborsClassifier(n_neighbors=2)
clf.fit(X_train, y_train)
print("Done!")
# parameters = [{'kernel': ['linear'], 'C': [1, 10, 100]},
#               {'kernel': ['poly'], 'degree': [1, 2, 3, 4]}]
# print("Building a classifier...")
# print("    parameters tuning...")
# clf = grid_search.GridSearchCV(svm.SVC(C=1), parameters, cv=10)
# clf.fit(document_feature_matrix, raw_bunch['target'])
# print("Done!")

print("score test cv:")
scores = cross_validation.cross_val_score(clf, document_feature_matrix, raw_bunch['target'], scoring=None,
                                          cv=10, n_jobs=1, verbose=0,
                                          fit_params=None, pre_dispatch='2*n_jobs')
print(scores)
# #
# print("Best parameters set found on development set:")
# print()
# print(clf.best_params_)
# print()
# print("Grid scores on development set:")
# print()
# for params, mean_score, scores in clf.grid_scores_:
#     print("%0.3f (+/-%0.03f) for %r"
#           % (mean_score, scores.std() * 2, feature_names[params]))
# print()

# print("Detailed classification report:")
# print()
# print("The model is trained on the full development set.")
# print("The scores are computed on the full evaluation set.")
# print()
print(metrics.classification_report(clf.predict(X_test), y_test))
print()