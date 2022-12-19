import matplotlib.pyplot as plt

if __name__ == '__main__':
    language = ['C', 'C++', 'Java', 'Python', 'PHP']
    students = [23, 11, 32, 22, 11]
    plt.bar(language, students)
    plt.show()
