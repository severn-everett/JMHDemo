import csv
import sys

import matplotlib.pyplot as plt

headers = []
throughput = []
err_range = []


def add_value(lc, c):
    if lc == 1:
        headers.append(c)
    elif lc == 2:
        throughput.append(float(c))
    elif lc == 3:
        err_range.append(float(c))


def populate_data():
    line_count = 1
    with open(sys.argv[1]) as csvfile:
        line_reader = csv.reader(csvfile)
        for row in line_reader:
            if line_count > 3:
                break
            for cell in row:
                add_value(line_count, cell)
            line_count += 1


if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Must provide exactly one argument")
        sys.exit(1)

    populate_data()

    ax = plt.subplots()[1]

    bar_chart = plt.bar(headers, throughput, yerr=err_range)
    plt.title("Serde Throughput")
    plt.ylabel("ops/s")
    plt.gca().get_yaxis().get_major_formatter().set_scientific(False)
    labels = ax.get_xticklabels()
    plt.setp(labels, rotation=45, horizontalalignment='right')
    plt.show()
